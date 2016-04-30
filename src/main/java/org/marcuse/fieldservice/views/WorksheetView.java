package org.marcuse.fieldservice.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.*;


import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorksheetView {

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private boolean visible;

	@Getter
	@Setter
	private long iteration;

	@Getter
	@Setter
	private boolean owner;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime creationDate;

	@Getter
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public LocalDateTime closeDate;

	@Getter
	@Setter
	private Assignment assignment;

	@Getter
	@Setter
	private List<WorksheetGroup> groups;

}