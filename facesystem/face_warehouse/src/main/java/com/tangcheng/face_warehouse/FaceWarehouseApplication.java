package com.tangcheng.face_warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi //开启swagger
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class FaceWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceWarehouseApplication.class, args);
	}

}
