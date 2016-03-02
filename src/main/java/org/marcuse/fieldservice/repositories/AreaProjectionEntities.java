package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "entities", types = Area.class)
public interface AreaProjectionEntities {

	long getId();

	int getNumber();

	City getCity();

}