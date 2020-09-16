package com.paytech.vita.jwt;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ClientWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("**/register/**").permitAll()
				.antMatchers("**/login/**").permitAll()
				.antMatchers("**/login/forgot-password/**").permitAll()
				.antMatchers("**/login/resend-otp/**").permitAll()
				.antMatchers("/actuator/**", "/api-docs/**").permitAll()
				.antMatchers("/**").authenticated();
	}
}
