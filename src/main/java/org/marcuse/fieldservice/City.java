package org.marcuse.fieldservice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Getter
	@Setter
	private String name;

}
