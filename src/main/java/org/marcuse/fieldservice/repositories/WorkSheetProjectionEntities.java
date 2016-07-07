package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.List;

@Projection(name = "entities", types = Worksheet.class)
public interface WorksheetProjectionEntities {

	long getId();

	boolean isActive();

	long getIteration();

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	LocalDateTime getCreationDate();

	Assignment getAssignment();

	List<Visit> getVisits();

}