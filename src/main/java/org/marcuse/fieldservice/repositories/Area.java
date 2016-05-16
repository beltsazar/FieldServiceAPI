package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private int number;

	@Lob
	@JsonRawValue
	@JsonDeserialize(using = AreaShapeDeserializer.class)
	@Getter
	@Setter
	private String shape;

	@Getter
	@ManyToOne
	private City city;

	@OneToMany(mappedBy = "area")
	private List<Address> addresses;

	@OneToMany(mappedBy = "area")
	private List<Assignment> assignments;

}


