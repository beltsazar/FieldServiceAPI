package org.marcuse.fieldservice.repositories;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danielmarcuse on 22/04/16.
 */

@Entity
public class Account {
	enum Roles {
		USER, ADMIN, ROOT
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@Getter
	@Setter
	private String username;

	@JsonIgnore
	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String role;

	@Getter
	@Setter
	private String firstName;

	@Getter
	@Setter
	private String infix;

	@Getter
	@Setter
	private String lastName;

	@Getter
	@Setter
	private String email;

}
