package com.inventory.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({
		"com.inventory.supplier",
		"com.inventory.sharedfiles"
})
public class SupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplierApplication.class, args);
	}

}
