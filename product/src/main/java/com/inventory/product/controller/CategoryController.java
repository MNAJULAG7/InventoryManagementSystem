package com.inventory.product.controller;

import com.inventory.product.dto.*;
import com.inventory.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/")
    public ResponseEntity<CategoryResponsePage> getall(
            @RequestParam(name="pageNumber", defaultValue = com.inventory.sharedfiles.AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
            @RequestParam(name="pageSize",defaultValue = com.inventory.sharedfiles.AppConstants.PAGE_SIZE,required = false)  Long pageSize,
            @RequestParam(name = "sortBy", defaultValue = com.inventory.sharedfiles.AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = com.inventory.sharedfiles.AppConstants.SORT_DIR,required = false) String sortDir)

    {
        return ResponseEntity.ok().body(service.getAll(pageNumber,pageSize,sortBy,sortDir));
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.inventory.sharedfiles.CategoryResponse> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.getById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<com.inventory.sharedfiles.CategoryResponse> add(@RequestBody CategoryRequest request)
    {
        return ResponseEntity.ok().body(service.add(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<com.inventory.sharedfiles.CategoryResponse> add(@PathVariable Long id, @RequestBody CategoryRequest request)
    {
        return ResponseEntity.ok().body(service.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String , Object>> delete(@PathVariable Long id)
    {   Map<String , Object> ans= new HashMap<>();
        ans.put("deleted item",service.delete(id));
        ans.put("status","deleted successfully");
        return ResponseEntity.ok().body(ans);
    }

    @PostMapping("/addall")
    public ResponseEntity<String> addall(@RequestBody List<CategoryRequest> request)
    {    service.addall(request);
        return ResponseEntity.ok().body("added successfully");
    }

}
