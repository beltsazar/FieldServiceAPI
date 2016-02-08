package org.marcuse.fieldservice;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full", types = Area.class)
public interface AreaProjectionFull {

    String getId();

    String getNumber();

    <List>Address getAddresses();

}