package com.paytech.vita.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payment")
@RestController
public class PaymentRestController {

	@GetMapping("/say-hi")
	public ResponseEntity<?> helloThere(){
		return ResponseEntity.ok("Hello there. I'm payment service!!!");
	}
}
