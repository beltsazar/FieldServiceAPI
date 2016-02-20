package org.marcuse.fieldservice.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisitHelper {

	@Autowired
	private ReportJpaRepository reportJpaRepository;

	public long sayHello(Address address) {

		List<Report> reports = reportJpaRepository.findByActive(true);

		return 1L;

		//List<Address> findByNumber(@Param("number") int number);


		//return address.getArea().getReports().get(0).isActive();


		//return address.getVisits().size();

	}

}