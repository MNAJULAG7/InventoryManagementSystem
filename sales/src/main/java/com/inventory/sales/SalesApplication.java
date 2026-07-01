package com.inventory.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({
		"com.inventory.sales",
		"com.inventory.sharedfiles"
})
public class SalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

}
