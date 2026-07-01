package com.inventory.report.servcie;

import com.inventory.sharedfiles.SaleReportResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(
        name = "SALES",
        path = "/api/sales"
)
public interface FromSalesService {

    @GetMapping("/report/sales")
    ResponseEntity<List<SaleReportResponse>> getSaleForReport();

    @GetMapping("/sales/date")
    ResponseEntity<List<SaleReportResponse>> getSaleBetweendatesForReport(
            @RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end
    );
}
