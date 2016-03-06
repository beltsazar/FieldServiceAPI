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

	@JsonIgnore
	@Getter
	@ManyToOne
	private Street street;

	@ManyToOne
	private Area area;

	@OneToMany(mappedBy = "address")
	private List<Visit> visits;

}
