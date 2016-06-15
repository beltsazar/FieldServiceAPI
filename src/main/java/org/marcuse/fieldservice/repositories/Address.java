package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private int number;

	@Getter
	@Setter
	private String suffix;

	@Getter
	@Setter
	@ManyToOne
	private Street street;

	@Getter
	@Setter
	@ManyToOne
	private City city;

	@Getter
	@Setter
	@ManyToOne
	private Area area;

	@Getter
	@OneToMany(mappedBy = "address")
	private List<Annotation> annotations;

	@OneToMany(mappedBy = "address")
	private List<Visit> visits;

}
