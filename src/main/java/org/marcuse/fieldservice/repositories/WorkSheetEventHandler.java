package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(WorkSheet.class)
public class WorkSheetEventHandler {

	@HandleBeforeCreate
	public void handleWorkSheetCreation(WorkSheet workSheet) {
		workSheet.setCreationDate(LocalDateTime.now());
		workSheet.setIteration(1);
	}

}