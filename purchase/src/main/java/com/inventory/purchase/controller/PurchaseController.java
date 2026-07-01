package com.inventory.purchase.controller;

import com.inventory.purchase.dto.PurchaseRequest;
import com.inventory.sharedfiles.PurchaseResponse;
import com.inventory.purchase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    @Autowired
    PurchaseService service;

    @PostMapping("/add")
    public ResponseEntity<PurchaseResponse> add(@RequestBody PurchaseRequest request)
    {
        return ResponseEntity.ok().body(service.add(request));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PurchaseResponse>> getAll()
    {
        return ResponseEntity.ok().body(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.getById(id));
    }

}
