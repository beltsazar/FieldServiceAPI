package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Visit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private boolean isSuccess;

	@Getter
	@Setter
	private long turn;

	@Setter
	@Getter
	public LocalDateTime creationDate;

	public String getCreationDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		return this.creationDate.format(formatter);
	}

}
