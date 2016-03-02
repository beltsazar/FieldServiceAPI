package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private boolean active;

	@Getter
	@Setter
	private boolean personal;

	@Getter
	@Setter
	public LocalDateTime creationDate;

	@ManyToOne
	private Area area;

	@OneToOne(mappedBy = "assignment")
	private WorkSheet workSheet;

}
