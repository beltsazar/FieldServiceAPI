package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Visit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private boolean success;

	@Getter
	@Setter
	private long iteration;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime creationDate;

	@Getter
	@Setter
	@ManyToOne
	private Address address;

	@Setter
	@ManyToOne
	private Worksheet worksheet;

}
