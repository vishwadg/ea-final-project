package com.example.servicediscoveries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscoveriesApplication.class, args);
	}

}
