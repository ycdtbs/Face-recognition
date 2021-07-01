package com.tangcheng.face_corn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi //开启swagger
@EnableFeignClients
public class FaceCornApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceCornApplication.class, args);
	}

}
