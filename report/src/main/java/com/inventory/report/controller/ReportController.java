package com.inventory.report.controller;

import com.inventory.report.dto.ReportProductPage;
import com.inventory.report.dto.ReportPurchaseResponse;
import com.inventory.report.dto.ReportSaleDateResponse;
import com.inventory.report.servcie.ReportServcie;
import com.inventory.sharedfiles.AppConstants;
import com.inventory.sharedfiles.PurchaseResponse;
import com.inventory.sharedfiles.SaleReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    ReportServcie reportServcie;

    @GetMapping("/stock")
    public ResponseEntity<ReportProductPage> getStocks(@RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Long pageNumber,
                                                       @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)  Long pageSize,
                                                       @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                       @RequestParam(name="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)

    {
        ReportProductPage r =  reportServcie.getStocks(pageNumber,pageSize,sortBy,sortDir);
       return  ResponseEntity.ok().body(r);
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SaleReportResponse>> getSales()
    {
        return ResponseEntity.ok().body(reportServcie.getSales());
    }

    @GetMapping("/sales/date")
    public ResponseEntity<ReportSaleDateResponse> getSaleDate(@RequestParam("start")LocalDate start, @RequestParam("end") LocalDate end)
    {
        return ResponseEntity.ok().body(reportServcie.getSaleDate(start,end));
    }

    @GetMapping("/purchases")
    public ResponseEntity<ReportPurchaseResponse> getPurchases()
    {
        return ResponseEntity.ok().body(reportServcie.getPurchases());
    }
}
