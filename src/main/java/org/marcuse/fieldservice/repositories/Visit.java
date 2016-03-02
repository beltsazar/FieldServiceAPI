package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
	public LocalDateTime creationDate;

	@Getter
	@ManyToOne
	private Address address;

	@ManyToOne
	private WorkSheet workSheet;

}
