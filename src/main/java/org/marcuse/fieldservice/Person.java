package org.marcuse.fieldservice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Getter
	@Setter
	private String lastName;

	@OneToOne
	@JoinColumn
	private Address address;

}
