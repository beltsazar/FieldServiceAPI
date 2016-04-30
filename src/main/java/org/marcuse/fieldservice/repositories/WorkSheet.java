package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Worksheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private boolean visible;

	@Getter
	@Setter
	private long iteration;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime creationDate;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime closeDate;

	@Getter
	@OneToOne
	private Assignment assignment;

	@OneToMany(mappedBy = "worksheet")
	private List<Visit> visits;

}
