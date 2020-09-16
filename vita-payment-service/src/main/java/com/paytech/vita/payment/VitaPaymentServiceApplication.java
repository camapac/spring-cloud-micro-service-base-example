package com.paytech.vita.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VitaPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitaPaymentServiceApplication.class, args);
	}

}
