package org.marcuse.fieldservice.repositories;

import org.marcuse.fieldservice.views.AreaView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "entities", types = Address.class)
public interface AddressProjectionEntities {

	long getId();

	long getNumber();

	String getSuffix();

	Street getStreet();

	City getCity();

	@Value("#{@areaUtils.getAreasByAddress(target)}")
	List<AreaView> getAreas();

}