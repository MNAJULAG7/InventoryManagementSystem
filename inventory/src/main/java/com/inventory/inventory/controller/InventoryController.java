package com.inventory.inventory.controller;

import com.inventory.inventory.dto.InventoryReposne;
import com.inventory.inventory.dto.ProductStocks;
import com.inventory.inventory.service.InventoryService;
import com.inventory.sharedfiles.ProductResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService service;

    @GetMapping("/get/available/stock")
    public ResponseEntity<InventoryReposne> getAvailablestocks
            (@RequestParam(name="pageNumber", defaultValue = com.inventory.sharedfiles.AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
             @RequestParam(name="pageSize",defaultValue = com.inventory.sharedfiles.AppConstants.PAGE_SIZE,required = false)  Long pageSize,
             @RequestParam(name = "sortBy", defaultValue = com.inventory.sharedfiles.AppConstants.SORT_BY,required = false) String sortBy,
             @RequestParam(name="sortDir",defaultValue = com.inventory.sharedfiles.AppConstants.SORT_DIR,required = false) String sortDir)
    {
        InventoryReposne p = service.getAvailablestocks(pageNumber,pageSize,sortBy,sortDir);
        return ResponseEntity.ok().body(p);
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<ProductStocks> getStockById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.getStockById(id));
    }

    @PutMapping("/update/stock/quantity/{id}")
    public ResponseEntity<ProductStocks> updateStockById(@PathVariable Long id,@RequestParam("quantity") Long quantity)
    {
        return ResponseEntity.ok().body(service.updateStockById(id,quantity));
    }
}
