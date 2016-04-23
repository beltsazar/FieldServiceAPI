package org.marcuse.fieldservice.views;


import org.marcuse.fieldservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@RestController
public class AuthorisationController {

	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping(path = "authorisation/login", method = RequestMethod.GET)
	public Account getAuthorisedAccount(Authentication authentication) {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return accountRepository.findByUsername(userDetails.getUsername());

	}

}
