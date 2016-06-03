package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(name = "entities", types = Assignment.class)
public interface AssignmentProjectionEntities {

	long getId();

	boolean getActive();

	boolean isPersonal();

	LocalDateTime getCreationDate();

	LocalDateTime getCloseDate();

	Area getArea();

	@Value("#{target.area.getCity().getName()} #{target.area.number}")
	String getAreaDisplayName();

	Account getAccount();

	Worksheet getWorksheet();

	Campaign getCampaign();

}