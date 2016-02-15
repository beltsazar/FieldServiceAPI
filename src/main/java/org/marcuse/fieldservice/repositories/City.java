package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private String name;

}
