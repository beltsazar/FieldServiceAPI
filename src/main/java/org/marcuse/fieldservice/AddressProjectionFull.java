package org.marcuse.fieldservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full", types = Address.class)
public interface AddressProjectionFull {

    String getNumber();

    Street getStreet();

    City getCity();
}