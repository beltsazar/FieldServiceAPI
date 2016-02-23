package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.utilities.PresentationHelper;

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

	@Setter
	public LocalDateTime creationDate;

	public String getCreationDate() {
		return PresentationHelper.formatDateTime(this.creationDate);
	}

	@Getter
	@ManyToOne
	private Address address;

	@ManyToOne
	private Report report;

}
