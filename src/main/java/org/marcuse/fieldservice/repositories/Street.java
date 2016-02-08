package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Street {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Getter
	@Setter
	private String name;

}
