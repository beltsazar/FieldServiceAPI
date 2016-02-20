package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(Visit.class)
public class VisitEventHandler {

	@HandleBeforeCreate
	public void handleVisitCreation(Visit visit) {
		visit.setCreationDate(LocalDateTime.now());
	}

}