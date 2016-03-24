package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(Worksheet.class)
public class WorksheetEventHandler {

	@HandleBeforeCreate
	public void handleWorksheetCreation(Worksheet worksheet) {
		worksheet.setCreationDate(LocalDateTime.now());
		worksheet.setActive(true);
		worksheet.setIteration(1);
	}

}