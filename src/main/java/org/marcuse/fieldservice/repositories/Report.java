package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.utilities.PresentationHelper;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Report {

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

	@Setter
	public LocalDateTime creationDate;

	public String getCreationDate() {
		return PresentationHelper.formatDateTime(this.creationDate);
	}

	@ManyToOne
	private Area area;

	@Getter
	@OneToMany(mappedBy = "report")
	private List<Visit> visits;

}
