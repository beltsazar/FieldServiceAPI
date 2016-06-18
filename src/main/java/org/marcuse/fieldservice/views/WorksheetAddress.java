package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.AddressAnnotation;
import org.marcuse.fieldservice.repositories.Visit;

import java.util.List;

/**
 * Created by danielmarcuse on 28/02/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorksheetAddress {

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private int number;

	@Getter
	@Setter
	private String suffix;

	@Getter
	@Setter
	private List<AddressAnnotation> addressAnnotations;

	@Getter
	@Setter
	private List<Visit> visits;
}
