package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full", types = Address.class)
public interface AddressProjectionFull {

    String getNumber();

    Street getStreet();

    City getCity();
}