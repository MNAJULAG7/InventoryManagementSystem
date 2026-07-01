package com.inventory.sales.controller;

import com.inventory.sales.dto.SaleRequest;
import com.inventory.sales.dto.SaleResponse;
import com.inventory.sales.service.SalesSerivce;
import com.inventory.sharedfiles.SaleReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
/* for report*/
    @GetMapping("/report/sales")
    public ResponseEntity<List<SaleReportResponse>> getSaleForReport()
    {
        return ResponseEntity.ok().body(serivce.getSaleForReport());
    }

    @GetMapping("/sales/date")
    public ResponseEntity<List<SaleReportResponse>> getSaleBetweendatesForReport(
            @RequestParam("start")LocalDate start,@RequestParam("end") LocalDate end
            )
    {
        return ResponseEntity.ok().body(serivce.getSaleBetweendatesForReport(start,end));
    }
}
