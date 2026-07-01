package com.inventory.purchase.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT",
        path = "/api/products")
public interface FromProduct {
    @PutMapping("/update/purchase")
    void updateProductByPurchase(@RequestParam("productId") Long productId, @RequestParam("quantity") Long quantity);

}
