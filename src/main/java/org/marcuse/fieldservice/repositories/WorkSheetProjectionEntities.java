package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.List;

@Projection(name = "entities", types = Worksheet.class)
public interface WorksheetProjectionEntities {

	long getId();

	boolean isActive();

	long getIteration();

	LocalDateTime getCreationDate();

	Assignment getAssignment();

	List<Visit> getVisits();

}