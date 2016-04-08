package org.marcuse.fieldservice.views;


import org.marcuse.fieldservice.repositories.*;
import org.marcuse.fieldservice.views.WorksheetAddress;
import org.marcuse.fieldservice.views.WorksheetGroup;
import org.marcuse.fieldservice.views.WorksheetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(path = "/worksheets/view", method = RequestMethod.GET)
	public List<WorksheetView> worksheetViewList() {

		List<WorksheetView> worksheetViewList = new ArrayList<WorksheetView>();

		worksheetRepository.findAll().forEach(worksheet -> worksheetViewList.add(worksheetView(worksheet.getId())));

		worksheetViewList.sort(new Comparator<WorksheetView>() {
			public int compare(WorksheetView v1, WorksheetView v2) {
				return (v1.getCreationDate()).compareTo(v2.getCreationDate());
			}
		});

		return worksheetViewList;
	}

	@RequestMapping(path = "/worksheets/{worksheetId}/view", method = RequestMethod.GET)
	public WorksheetView worksheetView(@PathVariable Long worksheetId) {

		WorksheetView worksheetView = new WorksheetView();

		Worksheet worksheet = worksheetRepository.findOne(worksheetId);

		Assignment assignment = worksheet.getAssignment();

		Area area = assignment.getArea();

		List<Address> addresses = addressesRepository.findByAreaOrderByStreetName(area);

		List<Visit> visits = visitRepository.findByWorksheet(worksheet);

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

		worksheetView.setId(worksheet.getId());
		worksheetView.setIteration(worksheet.getIteration());
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
