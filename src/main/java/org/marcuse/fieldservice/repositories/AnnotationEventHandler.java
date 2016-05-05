package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(Annotation.class)
public class AnnotationEventHandler {

	@HandleBeforeCreate
	public void handleAnnotationCreation(Annotation annotation) {
		annotation.setCreationDate(LocalDateTime.now());
		annotation.setModificationDate(LocalDateTime.now());
	}

}