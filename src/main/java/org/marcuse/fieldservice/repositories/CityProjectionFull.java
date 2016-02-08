package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full", types = City.class)
public interface CityProjectionFull {

    String getName();

}