package org.marcuse.fieldservice.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "entities", types = Address.class)
public interface AddressProjectionEntities {

	long getId();

	long getNumber();

	String getSuffix();

	Street getStreet();

	City getCity();

	Area getArea();

	@Value("#{target.area != null ? target.area.getCity().getName() : \"\"}")
	String getAreaCity();

}