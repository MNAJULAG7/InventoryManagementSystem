package com.inventory.sales.controller;

import com.inventory.sales.dto.SaleRequest;
import com.inventory.sales.dto.SaleResponse;
import com.inventory.sales.service.SalesSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    SalesSerivce serivce;

    @PostMapping("/add")
    public ResponseEntity<SaleResponse> add(@RequestBody SaleRequest request)
    {
        return ResponseEntity.ok().body(serivce.add(request));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<SaleResponse>> getAll()
    {
        return ResponseEntity.ok().body(serivce.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(serivce.getById(id));
    }

}
