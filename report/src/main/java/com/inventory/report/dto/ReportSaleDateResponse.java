package com.inventory.report.dto;

import com.inventory.sharedfiles.SaleReportResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaleDateResponse {
    private LocalDate startDate;
    private  LocalDate endDate;
    private Double totalSalesAmount;
    private List<SaleReportResponse> saleReportResponseList;
}
