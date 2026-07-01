package com.inventory.supplier.controller;

import com.inventory.supplier.dto.AppConstants;
import com.inventory.supplier.dto.SupplierRequest;
import com.inventory.supplier.dto.SupplierResponse;
import com.inventory.supplier.dto.SupplierResponsePage;
import com.inventory.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    SupplierService service;

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getallById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(service.getById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<SupplierResponse> add(@RequestBody SupplierRequest request)
    {
        return ResponseEntity.ok().body(service.add(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplierResponse> add(@PathVariable Long id,@RequestBody SupplierRequest request)
    {
        return ResponseEntity.ok().body(service.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("Supplier",service.delete(id));
        map.put("Status","deleted Successfully");
        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/addall")
    public ResponseEntity<String> addall(@RequestBody List<SupplierRequest> request)
    {   service.addall(request);
        return ResponseEntity.ok().body("added successfully");
    }
    @GetMapping("/getall")
    public ResponseEntity<SupplierResponsePage> getall(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Long pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)

    {
        return ResponseEntity.ok().body(service.getAll(pageNumber,pageSize,sortBy,sortDir));
    }

}
