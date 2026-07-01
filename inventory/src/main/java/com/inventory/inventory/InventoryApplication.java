package com.inventory.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({
		"com.inventory.inventory",
		"com.inventory.sharedfiles"
})
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
