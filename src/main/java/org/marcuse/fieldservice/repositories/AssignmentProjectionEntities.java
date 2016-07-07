package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.marcuse.fieldservice.views.AreaView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(name = "entities", types = Assignment.class)
public interface AssignmentProjectionEntities {

	long getId();

	boolean getActive();

	boolean isPersonal();

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	LocalDateTime getCreationDate();

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	LocalDateTime getCloseDate();

	@Value("#{@areaUtils.getAreaByAssignment(target)}")
	AreaView getArea();

	Account getAccount();

	Worksheet getWorksheet();

	Campaign getCampaign();

}