package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class WorkSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private boolean active;

	@Getter
	@Setter
	private long iteration;

	@Getter
	@Setter
	public LocalDateTime creationDate;

	@OneToOne
	private Assignment assignment;

	@Getter
	@OneToMany(mappedBy = "workSheet")
	private List<Visit> visits;

}
