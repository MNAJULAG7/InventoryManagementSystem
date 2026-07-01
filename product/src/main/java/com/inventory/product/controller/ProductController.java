package com.inventory.product.controller;

import com.inventory.sharedfiles.AppConstants;
import com.inventory.product.dto.ProductRequest;
import com.inventory.sharedfiles.ProductResponse;
import com.inventory.sharedfiles.ProductResponsePage;
import com.inventory.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/")
    public ResponseEntity<ProductResponsePage> getall(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
             @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Long pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)

    {
        return ResponseEntity.ok().body(service.getAll(pageNumber,pageSize,sortBy,sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getallById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.getById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> add(@RequestBody ProductRequest request)
    {
        return ResponseEntity.ok().body(service.add(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> add(@PathVariable Long id,@RequestBody ProductRequest request)
    {
        return ResponseEntity.ok().body(service.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.delete(id));
    }

    @PostMapping("/addall")
    public ResponseEntity<String> addall(@RequestBody List<ProductRequest> request)
    {   service.addall(request);
        return ResponseEntity.ok().body("added successfully");
    }





    /* for all other microservice */
    @PutMapping("/update/purchase")
    public void updateProductByPurchase(@RequestParam("productId") Long productId,@RequestParam("quantity") Long quantity)
    {
        service.updateProductByPurchase(productId,quantity);
    }


    @PutMapping("/update/sale")
    public boolean updateProductBySale(@RequestParam("productId") Long productId,@RequestParam("quantity") Long quantity)
    {
        return service.updateProductBySale(productId,quantity);
    }

    @GetMapping("/get/sale")
    public Double productPrice(@RequestParam("productId") Long productId)
    {
        return service.productPrice(productId);
    }

    @GetMapping("/get/stocks")
    public ResponseEntity<ProductResponsePage> getAvailablestocks(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Long pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)

    {
        return ResponseEntity.ok().body(service.getAvailablestocks(pageNumber,pageSize,sortBy,sortDir));
    }

    @PutMapping("/update/quantity/{id}")
    public boolean updateQuantityOfProduct(@PathVariable Long id,@RequestParam("quantity") Long quantity)
    {
        return service.updateQuantityOfProduct(id,quantity);
    }

}
