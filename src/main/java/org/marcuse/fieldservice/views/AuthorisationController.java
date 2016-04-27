package org.marcuse.fieldservice.views;


import lombok.Getter;
import lombok.Setter;
import org.marcuse.fieldservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@RestController
public class AuthorisationController {

	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping(path = "authorisation/login", method = RequestMethod.GET)
	public AuthorisationStatus login(Authentication authentication) {
		return isAuthenticated(authentication);
	}

	@RequestMapping(path = "authorisation/account", method = RequestMethod.GET)
	public Account getAuthorisedAccount(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return accountRepository.findByUsernameIgnoreCase(userDetails.getUsername());
	}

	@RequestMapping(path = "authorisation/status", method = RequestMethod.GET)
	public AuthorisationStatus isAuthenticated(Authentication authentication) {
		AuthorisationStatus authorisationStatus = new AuthorisationStatus();

		if (authentication != null) {
			authorisationStatus.setAuthenticated(true);
		}
		else {
			authorisationStatus.setAuthenticated(false);
		}

		return authorisationStatus;
	}

	class AuthorisationStatus {
		@Getter
		@Setter
		private boolean authenticated;
	}

}
