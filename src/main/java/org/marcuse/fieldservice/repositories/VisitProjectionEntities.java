package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "entities", types = Visit.class)
public interface VisitProjectionEntities {

	long getId();

	boolean isSuccess();

	long getIteration();

	LocalDateTime getCreationDate();

	Address getAddress();
	
}