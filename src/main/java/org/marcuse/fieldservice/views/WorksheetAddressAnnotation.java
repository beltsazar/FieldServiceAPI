package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.Address;
import org.marcuse.fieldservice.repositories.AddressAnnotation;
import org.marcuse.fieldservice.repositories.AddressAnnotationType;
import org.marcuse.fieldservice.repositories.Visit;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by danielmarcuse on 28/02/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorksheetAddressAnnotation {

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private AddressAnnotationType type;

	@Getter
	@Setter
	private String comment;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime creationDate;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime modificationDate;
}
