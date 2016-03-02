package org.marcuse.fieldservice.controllers;


import org.marcuse.fieldservice.repositories.*;
import org.marcuse.fieldservice.views.ReportView;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by danielmarcuse on 28/02/16.
 */

@RestController
public class ViewsController {

//	@Autowired
//	private AssignmentRepository assignmentRepository;
//
//	@Autowired
//	private AddressRepository addressesRepository;
//
//	@Autowired
//	private VisitRepository visitRepository;
//
//	@RequestMapping(path = "/viewreport", method = RequestMethod.GET)
//	public ReportView reportView() {
//
//		ReportView reportView = new ReportView();
//
//
//		//reportRepository.findOne(100L);
//
//
//		//return reportRepository.findOne(100L);
//
//		Assignment assignment = assignmentRepository.findOne(163L);
//
//		Area area = assignment.getArea();
//
//		List<Address> addresses = addressesRepository.findByArea(area, new PageRequest(0,10000)).getContent();
//
//		Iterator iterator = addresses.iterator();
//
//		while(iterator.hasNext()) {
//			Address address = (Address)iterator.next();
//
//			out.println(address.getId());
//
//			List<Visit> visits = visitRepository.findByReportAndAddress(assignment, address, new PageRequest(0,10000)).getContent();
//			out.println("visits--" + visits.size());
//
//		}
//
//
//		//out.println(addresses.getTotalElements());
//
//		reportView.setAssignment(assignment);
//		reportView.setArea(area);
//		reportView.setAddresses(addresses);
//
//
//		List<Address> addresses2 = (List) addressesRepository.findAll();
//
//		return reportView;
//
//
//	}



}
