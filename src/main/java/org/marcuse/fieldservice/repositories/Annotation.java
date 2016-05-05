package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Annotation {
	enum AnnotationType {
		INFO, WARNING, DO_NOT_VISIT
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private AnnotationType type;

	@Getter
	@Setter
	private String comment;

	@Getter
	@Setter
	@ManyToOne
	private Address address;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime creationDate;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime modificationDate;

}
