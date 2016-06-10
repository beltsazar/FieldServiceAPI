package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(name = "assignments", types = Area.class)
public interface AreaProjectionAssignments {

	long getId();

	int getNumber();

	String getType();

	String getShape();

	City getCity();

	//@Value("#{@areaUtils.filterAssignments(target)}")
	//List<Assignment> getAssignments();

}