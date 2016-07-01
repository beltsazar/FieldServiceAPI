package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.Address;
import org.marcuse.fieldservice.repositories.Visit;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by danielmarcuse on 28/02/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorksheetVisit {

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private boolean success;

	@Getter
	@Setter
	private long iteration;

	@Getter
	@Setter
	private Address address;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime creationDate;
}
