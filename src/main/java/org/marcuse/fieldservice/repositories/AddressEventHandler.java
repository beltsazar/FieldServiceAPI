package org.marcuse.fieldservice.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler(Address.class)
public class AddressEventHandler {

	@HandleBeforeCreate
	@HandleBeforeSave
	public void handleSuffixFormat(Address address) {
		String suffix = address.getSuffix();
		if (suffix != null) {
			address.setSuffix(suffix.toUpperCase());
		}
	}

}