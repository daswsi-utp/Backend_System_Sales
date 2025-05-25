package com.microservice.registry.microservice_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRegistryApplication.class, args);
	}

}
