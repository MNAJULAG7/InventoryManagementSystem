package com.inventory.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({
		"com.inventory.product",
		"com.inventory.sharedfiles"
})
//@Import(com.inventory.sharedfiles.BeanCreation.class)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
