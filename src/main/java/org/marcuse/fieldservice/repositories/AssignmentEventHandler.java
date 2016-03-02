package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(Assignment.class)
public class AssignmentEventHandler {

	@HandleBeforeCreate
	public void handleAssignmentCreation(Assignment assignment) {
		assignment.setCreationDate(LocalDateTime.now());
	}

}