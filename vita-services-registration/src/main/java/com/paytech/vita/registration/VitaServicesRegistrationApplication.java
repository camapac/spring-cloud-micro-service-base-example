package com.paytech.vita.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class VitaServicesRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitaServicesRegistrationApplication.class, args);
	}

}
