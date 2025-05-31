package com.microservice.registry.microservice_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.microservice.registry.microservice_registry.client")
@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRegistryApplication.class, args);
	}

}
