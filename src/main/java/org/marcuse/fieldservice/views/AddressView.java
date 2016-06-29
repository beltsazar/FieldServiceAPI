package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.*;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressView {

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
	private String street;

	@Getter
	@Setter
	private String city;

}
