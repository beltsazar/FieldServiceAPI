package org.marcuse.fieldservice.configuration;

import org.marcuse.fieldservice.repositories.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Address.class, Street.class, City.class, Area.class, Worksheet.class, Visit.class, Assignment.class, Account.class, AddressAnnotation.class, Campaign.class);
    }

}