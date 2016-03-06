package org.marcuse.fieldservice.controllers;


import org.marcuse.fieldservice.repositories.*;
import org.marcuse.fieldservice.views.WorksheetGroup;
import org.marcuse.fieldservice.views.WorksheetView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by danielmarcuse on 28/02/16.
 */

@RestController
@RequestMapping("/worksheets")
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

	@RequestMapping(path = "/{worksheetId}", method = RequestMethod.GET)
	public WorksheetView worksheetView(@PathVariable Long worksheetId) {

		WorksheetView worksheetView = new WorksheetView();

		Worksheet worksheet = worksheetRepository.findOne(worksheetId);

		Assignment assignment = worksheet.getAssignment();

		Area area = assignment.getArea();

		List<Address> addresses = addressesRepository.findByAreaOrderByStreetName(area);

		List<Visit> visits = visitRepository.findByWorksheet(worksheet);

		HashSet<Street> streetHashSet = new HashSet<>();

		List<WorksheetGroup> worksheetGroups = new ArrayList<>();

		addresses.forEach(address -> streetHashSet.add(address.getStreet()));

		streetHashSet.forEach(street -> {
			WorksheetGroup worksheetGroup = new WorksheetGroup();
			worksheetGroup.setStreet(street);
			worksheetGroup.setAddresses(new ArrayList<>());
			worksheetGroups.add(worksheetGroup);
		});

		addresses.forEach(address -> {
			WorksheetGroup worksheetGroup = getWorksheetGroupByStreet(address.getStreet(), worksheetGroups);

			if (worksheetGroup != null) {
				worksheetGroup.getAddresses().add(address);
			}

		});

		worksheetGroups.sort(new Comparator<WorksheetGroup>() {
			public int compare(WorksheetGroup v1, WorksheetGroup v2) {
				return v1.getStreet().getName().compareTo(v2.getStreet().getName());
			}
		});

		worksheetGroups.forEach(worksheetGroup ->
			worksheetGroup.getAddresses().sort(new Comparator<Address>() {
				public int compare(Address v1, Address v2) {
					return ((Integer) v1.getNumber()).compareTo(v2.getNumber());
				}
			})
		);

		worksheetView.setActive(worksheet.isActive());
		worksheetView.setIteration(worksheet.getIteration());
		worksheetView.setGroups(worksheetGroups);

		return worksheetView;
	}

	private WorksheetGroup getWorksheetGroupByStreet(Street street, List<WorksheetGroup> worksheetGroups) {
		WorksheetGroup matchingWorksheetGroup;

		for (WorksheetGroup worksheetGroup : worksheetGroups) {
			if (street.equals(worksheetGroup.getStreet())) {
				return worksheetGroup;
			}

		}

		return null;

	}


}
