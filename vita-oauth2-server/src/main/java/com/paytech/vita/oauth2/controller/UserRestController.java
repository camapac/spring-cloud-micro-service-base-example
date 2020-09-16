package com.paytech.vita.oauth2.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/userinfo")
@RestController
public class UserRestController {

	@GetMapping
	public ResponseEntity<Principal> get(final Principal principal) {
		return ResponseEntity.ok(principal);
	}

}
