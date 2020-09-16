package com.paytech.vita.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paytech.vita.oauth2.entity.User;
import com.paytech.vita.oauth2.repository.UserRepository;

@Service(value = "userDetailsService")
public class CustomUserServiceDetail implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

		User user = null;
		if (input.contains("@"))
			user = userRepository.findByEmail(input);
		else
			user = userRepository.findByUsername(input);
		if (user == null)
			throw new BadCredentialsException("Bad credentials");

		new AccountStatusUserDetailsChecker().check(user);
		return user;

	}

}
