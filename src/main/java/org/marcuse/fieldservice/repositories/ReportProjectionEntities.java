package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "entities", types = Report.class)
public interface ReportProjectionEntities {

	long getId();

	boolean isActive();

	long getIteration();

	String getCreationDate();

	Area getArea();

}