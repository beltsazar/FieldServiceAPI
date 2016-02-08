package org.marcuse.fieldservice.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Getter
	@Setter
	private int number;

	@OneToMany(mappedBy = "area")
	private List<Address> addresses;
}
