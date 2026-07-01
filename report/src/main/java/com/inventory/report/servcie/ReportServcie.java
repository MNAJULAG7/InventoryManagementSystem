package com.inventory.report.servcie;

import com.inventory.report.dto.ReportProductPage;
import com.inventory.report.dto.ReportSaleDateResponse;
import com.inventory.sharedfiles.SaleReportResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReportServcie {
    ReportProductPage getStocks(Long pageNumber, Long pageSize, String sortBy, String sortDir);

    List<SaleReportResponse> getSales();

    ReportSaleDateResponse getSaleDate(LocalDate start, LocalDate end);
}
