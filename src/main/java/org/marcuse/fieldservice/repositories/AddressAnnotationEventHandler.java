package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(AddressAnnotation.class)
public class AddressAnnotationEventHandler {

	@HandleBeforeCreate
	public void handleAddressAnnotationCreation(AddressAnnotation addressAnnotation) {
		addressAnnotation.setCreationDate(LocalDateTime.now());
		addressAnnotation.setModificationDate(LocalDateTime.now());
	}

}