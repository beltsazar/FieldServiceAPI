package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Area {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private int number;

	@Getter
	@Setter
	private AreaType type;

	@Lob
	@JsonRawValue
	@JsonDeserialize(using = AreaShapeDeserializer.class)
	@Getter
	@Setter
	private String shape;

	@Getter
	@Setter
	@ManyToOne
	private City city;

	@ManyToMany(mappedBy = "areas")
	private List<Address> addresses;

	@OneToMany(mappedBy = "area")
	private List<Assignment> assignments;

}


