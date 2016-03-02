package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.Date;

@Projection(name = "entities", types = Assignment.class)
public interface AssignmentProjectionEntities {

	long getId();

	boolean getActive();

	boolean isPersonal();

	LocalDateTime getCreationDate();

	Area getArea();

	WorkSheet getWorkSheet();

}