package org.marcuse.fieldservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/**").fullyAuthenticated()
				.and().httpBasic()
				.and().csrf().csrfTokenRepository(csrfTokenRepository())
				.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
//		auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
//	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}


}