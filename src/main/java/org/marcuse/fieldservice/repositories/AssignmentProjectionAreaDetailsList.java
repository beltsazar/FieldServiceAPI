package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.marcuse.fieldservice.views.AreaView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(name = "areaDetailsList", types = Assignment.class)
public interface AssignmentProjectionAreaDetailsList {

	long getId();

	boolean getActive();

	boolean isPersonal();

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	LocalDateTime getCreationDate();

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	LocalDateTime getCloseDate();

//	@Value("#{@areaUtils.getAreaByAssignment(target)}")
//	AreaView getArea();

	Account getAccount();

//	Worksheet getWorksheet();

	Campaign getCampaign();

}