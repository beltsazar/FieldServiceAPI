package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.*;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaView {

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private int number;

	@Getter
	@Setter
	private String type;

	@Getter
	@Setter
	private String shape;

	@Getter
	@Setter
	private City city;

	@Getter
	@Setter
	private List<Assignment> assignments;

}


