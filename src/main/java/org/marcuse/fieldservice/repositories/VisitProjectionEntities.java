package org.marcuse.fieldservice.repositories;

import org.marcuse.fieldservice.utilities.PresentationHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "entities", types = Visit.class)
public interface VisitProjectionEntities {

	long getId();

	boolean isSuccess();

	long getIteration();

	String getCreationDate();

	Address getAddress();
	
}