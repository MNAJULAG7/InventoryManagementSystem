package com.inventory.report.servcie;

import com.inventory.sharedfiles.AppConstants;

import com.inventory.sharedfiles.ProductResponsePage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT",
        path = "/api/products")
interface FromProductService {

    @GetMapping("/get/stocks")
    ResponseEntity<ProductResponsePage> getAvailablestocks(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Long pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir);


}

