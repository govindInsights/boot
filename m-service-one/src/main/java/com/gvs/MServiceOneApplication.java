package com.gvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MServiceOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MServiceOneApplication.class, args);
	}

}
