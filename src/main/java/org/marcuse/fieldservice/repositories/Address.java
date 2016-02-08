package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Getter
	@Setter
	private int number;

	@ManyToOne
	private City city;

	@ManyToOne
	private Street street;

	@ManyToOne
	private Area area;

}
