package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private long turn;

	@Setter
	public LocalDateTime creationDate;

	// Format datetime into nice string
	public String getCreationDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		return this.creationDate.format(formatter);
	}

	@ManyToOne
	private Address address;

	@ManyToOne
	private Report report;

}
