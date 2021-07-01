package com.tangcheng.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurakeApplication.class, args);
	}

}
