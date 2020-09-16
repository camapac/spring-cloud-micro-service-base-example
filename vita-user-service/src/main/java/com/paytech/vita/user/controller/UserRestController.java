package com.paytech.vita.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/profile")
@RestController
public class UserRestController {

	@GetMapping("/say-hi")
	public ResponseEntity<?> helloThere(){
		return ResponseEntity.ok("Hello there. I'm user service!!!");
	}
}
