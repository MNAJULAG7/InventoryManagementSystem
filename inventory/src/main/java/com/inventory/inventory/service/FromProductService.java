package com.inventory.inventory.service;


import com.inventory.sharedfiles.AppConstants;
import com.inventory.sharedfiles.ProductResponse;
import com.inventory.sharedfiles.ProductResponsePage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.CacheRequest;

@FeignClient(name = "PRODUCT",
        path = "/api/products")
public interface FromProductService {

    @GetMapping("/get/stocks")
    ResponseEntity<ProductResponsePage> getAvailablestocks(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Long pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir);

    @PutMapping("/update/quantity/{id}")
    boolean updateQuantityOfProduct(@PathVariable Long id, @RequestParam("quantity") Long quantity);

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getallById(@PathVariable Long id);

}
