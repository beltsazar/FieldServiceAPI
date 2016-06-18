package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

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
	@ManyToMany
	private List<Area> areas;

	@Getter
	@OneToMany(mappedBy = "address")
	private List<AddressAnnotation> addressAnnotations;

	@OneToMany(mappedBy = "address")
	private List<Visit> visits;

}
