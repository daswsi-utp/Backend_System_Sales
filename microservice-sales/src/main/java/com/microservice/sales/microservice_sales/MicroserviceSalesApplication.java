package com.microservice.sales.microservice_sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSalesApplication.class, args);
	}

}
