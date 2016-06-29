package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.marcuse.fieldservice.views.AddressView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Projection(name = "entities", types = AddressAnnotation.class)
public interface AddressAnnotationProjectionEntities {

	long getId();

	AddressAnnotationType getType();

	String getComment();

	@Value("#{@addressUtils.getAddressByAnnotation(target)}")
	AddressView getAddress();

	LocalDateTime getCreationDate();

}