package org.marcuse.fieldservice.views;


import org.marcuse.fieldservice.repositories.*;
import org.marcuse.fieldservice.views.WorksheetAddress;
import org.marcuse.fieldservice.views.WorksheetGroup;
import org.marcuse.fieldservice.views.WorksheetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class WorksheetViewController {

	@Autowired
	private WorksheetRepository worksheetRepository;

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private AddressRepository addressesRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private StreetRepository streetRepository;

	@Autowired
	private VisitRepository visitRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CampaignRepository campaignRepository;

	@RequestMapping(path = "/api/worksheets/view", method = RequestMethod.GET)
	public List<WorksheetView> worksheetViewList(
			@RequestParam(value = "filter", required = false) ArrayList<String> filter) {

		List<WorksheetView> worksheetViewList = new ArrayList<WorksheetView>();

		if(filter != null && filter.contains("visible")) {
			worksheetRepository.findByVisible(true).forEach(worksheet -> {
				WorksheetView worksheetView = worksheetView(worksheet.getId());
				if(worksheetView.getAssignment().isActive()) {
					worksheetViewList.add(worksheetView);
				}
			});
		}
		else if(filter != null && filter.contains("me")) {

			worksheetRepository.findAll().forEach(
				worksheet -> {
					WorksheetView worksheetView = worksheetView(worksheet.getId());
					if (worksheetView.isOwner() && worksheetView.getAssignment().isActive()) {
						worksheetViewList.add(worksheetView);
					}
				}
			);

		}
		else {
			worksheetRepository.findAll().forEach(worksheet -> worksheetViewList.add(worksheetView(worksheet.getId())));
		}

		/**
		 * Filter by active campaign (if any)
		 */
		if(filter != null && filter.contains("campaign")) {

			Campaign activeCampaign;
			List<Campaign> activeCampaigns = campaignRepository.findByActive(true);
			if(activeCampaigns.size() > 0) {
				activeCampaign = activeCampaigns.get(0);
			}
			else {
				activeCampaign = null;
			}

			ListIterator<WorksheetView> worksheetViewListIterator = worksheetViewList.listIterator();

			if(activeCampaign == null) {
				while(worksheetViewListIterator.hasNext()) {
					WorksheetView worksheetView = worksheetViewListIterator.next();
					if(worksheetView.getAssignment().getCampaign() != null) {
						worksheetViewListIterator.remove();
					}
				}
			}
			else {
				while(worksheetViewListIterator.hasNext()) {
					WorksheetView worksheetView = worksheetViewListIterator.next();
					if (!activeCampaign.equals(worksheetView.getAssignment().getCampaign())) {
						worksheetViewListIterator.remove();
					}
				}
			}
		}

		worksheetViewList.sort(new Comparator<WorksheetView>() {
			public int compare(WorksheetView v1, WorksheetView v2) {
				String city1 = v1.getAssignment().getArea().getCity().getName();
				String city2 = v2.getAssignment().getArea().getCity().getName();

				int compareResult = city1.compareTo(city2);

				if (compareResult == 0) {
					Integer number1 = v1.getAssignment().getArea().getNumber();
					Integer number2 = v2.getAssignment().getArea().getNumber();

					return number1.compareTo(number2);
				}
				else {
					return compareResult;
				}

			}
		});

		return worksheetViewList;
	}

	@RequestMapping(path = "/api/worksheets/{worksheetId}/view", method = RequestMethod.GET)
	public WorksheetView worksheetView(@PathVariable Long worksheetId) {

		WorksheetView worksheetView = new WorksheetView();

		Worksheet worksheet = worksheetRepository.findOne(worksheetId);

		Assignment assignment = worksheet.getAssignment();

		Area area = assignment.getArea();

		List<Address> addresses = addressesRepository.findByAreaOrderByStreetName(area);

		List<Visit> visits = visitRepository.findByWorksheet(worksheet);

		/**
		 * Get the current logged in Account
		 */
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Account currentAccount = accountRepository.findByUsernameIgnoreCase(userDetails.getUsername());

		/**
		 * Create a worksheet group for each unique street in the collection of addresses
		 */
		HashSet<Street> streetHashSet = new HashSet<>();

		addresses.forEach(address -> streetHashSet.add(address.getStreet()));

		List<WorksheetGroup> worksheetGroups = new ArrayList<>();

		streetHashSet.forEach(street -> {
			WorksheetGroup worksheetGroup = new WorksheetGroup();
			worksheetGroup.setStreet(street);
			worksheetGroup.setAddresses(new ArrayList<>());
			worksheetGroups.add(worksheetGroup);
		});

		/**
		 * Add visits to each address and assign address to the correct group by street
		 */
		addresses.forEach(address -> {
			WorksheetGroup worksheetGroup = getWorksheetGroupByStreet(address.getStreet(), worksheetGroups);
			List<Visit> addressVisits = new ArrayList<>();

			if (worksheetGroup != null) {
				WorksheetAddress worksheetAddress = new WorksheetAddress();
				worksheetAddress.setId(address.getId());
				worksheetAddress.setNumber(address.getNumber());
				worksheetAddress.setSuffix(address.getSuffix());

				List<Annotation> annotations = address.getAnnotations();
				if(annotations.size() > 0) {
					annotations.forEach(annotation -> annotation.setAddress(null));
					worksheetAddress.setAnnotations(annotations);
				}

				addressVisits = getVisitsByAddress(address, visits);

				if(addressVisits.size() > 0) {
					addressVisits.sort(new Comparator<Visit>() {
						public int compare(Visit v1, Visit v2) {
							return ((Long) v1.getIteration()).compareTo(v2.getIteration());
						}
					});
					worksheetAddress.setVisits(addressVisits);
				}
				else {
					worksheetAddress.setVisits(null);
				}

				worksheetGroup.getAddresses().add(worksheetAddress);
			}

		});

		/**
		 * Sort worksheet group by street name
		 */
		worksheetGroups.sort(new Comparator<WorksheetGroup>() {
			public int compare(WorksheetGroup v1, WorksheetGroup v2) {
				return v1.getStreet().getName().compareTo(v2.getStreet().getName());
			}
		});

		/**
		 * Sort each address in the groups by house number
		 */
		worksheetGroups.forEach(worksheetGroup ->
			worksheetGroup.getAddresses().sort(new Comparator<WorksheetAddress>() {
				public int compare(WorksheetAddress v1, WorksheetAddress v2) {
					return ((Integer) v1.getNumber()).compareTo(v2.getNumber());
				}
			})
		);

		/**
		 * Remove address object from visit
		 */
		visits.forEach(visit -> visit.setAddress(null));

		/**
		 * Fill the JSON object
		 */
		worksheetView.setId(worksheet.getId());
		worksheetView.setVisible(worksheet.isVisible());
		worksheetView.setIteration(worksheet.getIteration());
		worksheetView.setOwner(currentAccount.equals(worksheet.getAssignment().getAccount()));
		worksheetView.setCreationDate(worksheet.getCreationDate());
		worksheetView.setCloseDate(worksheet.getCloseDate());
		worksheetView.setAssignment(assignment);
		worksheetView.setGroups(worksheetGroups);

		return worksheetView;
	}

	private WorksheetGroup getWorksheetGroupByStreet(Street street, List<WorksheetGroup> worksheetGroups) {

		for (WorksheetGroup worksheetGroup : worksheetGroups) {
			if (street.equals(worksheetGroup.getStreet())) {
				return worksheetGroup;
			}

		}

		return null;
	}

	private List<Visit> getVisitsByAddress(Address address, List<Visit> visits) {

		List<Visit> matchingVisits = new ArrayList<>();

		for (Visit visit : visits) {
			if (address.equals(visit.getAddress())) {
				matchingVisits.add(visit);
			}

		}

		return matchingVisits;
	}


}
