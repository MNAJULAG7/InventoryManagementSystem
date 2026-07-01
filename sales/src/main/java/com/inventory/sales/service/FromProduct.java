package com.inventory.sales.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT",path = "/api/products")
public interface FromProduct {
    @PutMapping("/update/sale")
    boolean updateProductBySale(@RequestParam("productId") Long productId, @RequestParam("quantity") Long quantity);

    @GetMapping("/get/sale")
    Double productPrice(@RequestParam("productId") Long productId);
}
