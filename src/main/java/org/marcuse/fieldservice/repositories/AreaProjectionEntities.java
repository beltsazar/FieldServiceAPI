package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.rest.core.config.Projection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(name = "entities", types = Area.class)
public interface AreaProjectionEntities {

	long getId();

	int getNumber();

	String getType();

	String getShape();

	City getCity();

}