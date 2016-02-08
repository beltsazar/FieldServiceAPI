package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full", types = Street.class)
public interface StreetProjectionFull {

    String getName();

}