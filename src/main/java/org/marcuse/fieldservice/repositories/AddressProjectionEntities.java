package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "entities", types = Address.class)
public interface AddressProjectionEntities {

	long getNumber();

	Street getStreet();

    City getCity();

	Area getArea();

}