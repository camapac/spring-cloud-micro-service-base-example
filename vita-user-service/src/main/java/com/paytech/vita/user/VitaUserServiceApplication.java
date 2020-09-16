package com.paytech.vita.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.paytech.vita.jwt.EnableJWTSecurity;

@SpringBootApplication
@EnableEurekaClient
@EnableJWTSecurity
public class VitaUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitaUserServiceApplication.class, args);
	}

	
}
