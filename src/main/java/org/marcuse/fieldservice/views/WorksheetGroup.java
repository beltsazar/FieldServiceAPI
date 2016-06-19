package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.*;

import java.util.List;

/**
 * Created by danielmarcuse on 28/02/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorksheetGroup {

	@Getter
	@Setter
	private City city;

	@Getter
	@Setter
	private Street street;

	@Getter
	@Setter
	private List<WorksheetAddress> addresses;

}
