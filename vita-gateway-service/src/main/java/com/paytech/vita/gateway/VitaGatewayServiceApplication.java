package com.paytech.vita.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VitaGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitaGatewayServiceApplication.class, args);
	}

}
