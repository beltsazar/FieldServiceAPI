package org.marcuse.fieldservice.views;


import org.marcuse.fieldservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

		if(filter != null && filter.contains("shared")) {
			worksheetRepository.findByShared(true).forEach(worksheet -> {
				WorksheetView worksheetView = worksheetView(worksheet.getId());
				worksheetViewList.add(worksheetView);
			});
		}
		else if(filter != null && filter.contains("me")) {

			worksheetRepository.findAll().forEach(
				worksheet -> {
					WorksheetView worksheetView = worksheetView(worksheet.getId());
					if (worksheetView.isOwner()) {
						worksheetViewList.add(worksheetView);
					}
				}
			);

		}
		else {
			worksheetRepository.findAll().forEach(worksheet -> worksheetViewList.add(worksheetView(worksheet.getId())));
		}

		/**
		 * Filter by active worksheet status
		 */
		if(filter != null && filter.contains("active")) {
			ListIterator<WorksheetView> worksheetViewListIterator = worksheetViewList.listIterator();

			while(worksheetViewListIterator.hasNext()) {
				WorksheetView worksheetView = worksheetViewListIterator.next();
				if (!worksheetView.getAssignment().isActive()) {
					worksheetViewListIterator.remove();
				}
			}

		}

		/**
		 * Filter by active campaign (if any)
		 */
		if(filter != null && filter.contains("campaign")) {

			List<Campaign> activeCampaigns = campaignRepository.findByActive(true);
			if(activeCampaigns.size() > 0) {
				Campaign activeCampaign = activeCampaigns.get(0);

				ListIterator<WorksheetView> worksheetViewListIterator = worksheetViewList.listIterator();

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

		List<Address> addresses = addressesRepository.findByAreasOrderByCityNameAscStreetNameAsc(area);

		List<WorksheetVisit> visits = new ArrayList<>();

		visitRepository.findByWorksheet(worksheet).forEach(visit -> {
			WorksheetVisit worksheetVisit = new WorksheetVisit();

			worksheetVisit.setId(visit.getId());
			worksheetVisit.setIteration(visit.getIteration());
			worksheetVisit.setSuccess(visit.isSuccess());
			worksheetVisit.setCreationDate(visit.getCreationDate());
			worksheetVisit.setAddress(visit.getAddress());

			visits.add(worksheetVisit);
		});

		/**
		 * Get the current logged in Account
		 */
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Account currentAccount = accountRepository.findByUsernameIgnoreCase(userDetails.getUsername());

		/**
		 * Create a worksheet group for each unique city/street in the collection of addresses
		 */

		List<WorksheetGroup> worksheetGroups = new ArrayList<>();
		ListIterator<Address> addressIterator = addresses.listIterator();

		Address previousAddress = null;

		while (addressIterator.hasNext()) {
			Address currentAddress = addressIterator.next();

			if (previousAddress == null || (!currentAddress.getCity().equals(previousAddress.getCity()) || !currentAddress.getStreet().equals(previousAddress.getStreet()))) {
				WorksheetGroup worksheetGroup = new WorksheetGroup();
				worksheetGroup.setStreet(currentAddress.getStreet());
				worksheetGroup.setCity(currentAddress.getCity());
				worksheetGroup.setAddresses(new ArrayList<>());
				worksheetGroups.add(worksheetGroup);
			}

			previousAddress = currentAddress;
		}

		/**
		 * Add visits to each address and assign address to the correct group by street
		 */

		addresses.forEach(address -> {
			WorksheetGroup worksheetGroup = getWorksheetGroupByCityAndStreet(address.getCity(), address.getStreet(), worksheetGroups);
			List<WorksheetVisit> addressVisits = new ArrayList<>();

			if (worksheetGroup != null) {
				WorksheetAddress worksheetAddress = new WorksheetAddress();
				worksheetAddress.setId(address.getId());
				worksheetAddress.setNumber(address.getNumber());
				worksheetAddress.setSuffix(address.getSuffix());

				List<AddressAnnotation> addressAnnotations = address.getAddressAnnotations();
				if(addressAnnotations.size() > 0) {
					List<WorksheetAddressAnnotation> worksheetAddressAnnotations = new ArrayList<>();

					addressAnnotations.forEach(annotation -> {
						WorksheetAddressAnnotation worksheetAddressAnnotation = new WorksheetAddressAnnotation();

						worksheetAddressAnnotation.setId(annotation.getId());
						worksheetAddressAnnotation.setComment(annotation.getComment());
						worksheetAddressAnnotation.setType(annotation.getType());
						worksheetAddressAnnotation.setCreationDate(annotation.getCreationDate());
						worksheetAddressAnnotation.setModificationDate(annotation.getModificationDate());

						worksheetAddressAnnotations.add(worksheetAddressAnnotation);
					});


					worksheetAddress.setAddressAnnotations(worksheetAddressAnnotations);
				}

				addressVisits = getVisitsByAddress(address, visits);

				if(addressVisits.size() > 0) {
					addressVisits.sort(new Comparator<WorksheetVisit>() {
						public int compare(WorksheetVisit v1, WorksheetVisit v2) {
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
		 * Sort worksheet group by city and street name
		 */
		worksheetGroups.sort(new Comparator<WorksheetGroup>() {
			public int compare(WorksheetGroup v1, WorksheetGroup v2) {
				int compareResult = v1.getCity().getName().compareTo(v2.getCity().getName());

				if (compareResult == 0) {
					return v1.getStreet().getName().compareTo(v2.getStreet().getName());
				}
				else {
					return compareResult;
				}
			}
		});

		/**
		 * Sort each address in the groups by house number
		 */
		worksheetGroups.forEach(worksheetGroup ->
			worksheetGroup.getAddresses().sort(new Comparator<WorksheetAddress>() {
				public int compare(WorksheetAddress v1, WorksheetAddress v2) {
					int compareResult = ((Integer) v1.getNumber()).compareTo(v2.getNumber());

					if (compareResult == 0) {
						String suffix1 = (v1.getSuffix() != null) ? v1.getSuffix() : "";
						String suffix2 = (v2.getSuffix() != null) ? v2.getSuffix() : "";

						return suffix1.compareTo(suffix2);
					}
					else {
						return compareResult;
					}

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
		worksheetView.setShared(worksheet.isShared());
		worksheetView.setIteration(worksheet.getIteration());
		worksheetView.setOwner(currentAccount.equals(worksheet.getAssignment().getAccount()));
		worksheetView.setCreationDate(worksheet.getCreationDate());
		worksheetView.setCloseDate(worksheet.getCloseDate());
		worksheetView.setAssignment(assignment);
		worksheetView.setGroups(worksheetGroups);

		return worksheetView;
	}

	private WorksheetGroup getWorksheetGroupByCityAndStreet(City city, Street street, List<WorksheetGroup> worksheetGroups) {

		for (WorksheetGroup worksheetGroup : worksheetGroups) {
			if (street.equals(worksheetGroup.getStreet()) && city.equals(worksheetGroup.getCity())) {
				return worksheetGroup;
			}

		}

		return null;
	}

	private List<WorksheetVisit> getVisitsByAddress(Address address, List<WorksheetVisit> visits) {

		List<WorksheetVisit> matchingVisits = new ArrayList<>();

		for (WorksheetVisit visit : visits) {
			if (address.equals(visit.getAddress())) {
				matchingVisits.add(visit);
			}

		}

		return matchingVisits;
	}


}
