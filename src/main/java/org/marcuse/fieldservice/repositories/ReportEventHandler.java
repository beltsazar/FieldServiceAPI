package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(Report.class)
public class ReportEventHandler {

	@HandleBeforeCreate
	public void handleReportCreation(Report report) {
		report.setCreationDate(LocalDateTime.now());
		report.setIteration(1);
	}

}