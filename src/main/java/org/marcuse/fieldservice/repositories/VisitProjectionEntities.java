package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "entities", types = Visit.class)
public interface VisitProjectionEntities {

	long getId();

	boolean isSuccess();

	long getIteration();

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	LocalDateTime getCreationDate();

	Address getAddress();
	
}