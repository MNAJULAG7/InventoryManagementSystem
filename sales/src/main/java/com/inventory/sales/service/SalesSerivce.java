package com.inventory.sales.service;

import com.inventory.sales.dto.SaleRequest;
import com.inventory.sales.dto.SaleResponse;
import com.inventory.sharedfiles.SaleReportResponse;

import java.time.LocalDate;
import java.util.List;

public interface SalesSerivce {
    SaleResponse add(SaleRequest request);

    List<SaleResponse> getAll();

    SaleResponse getById(Long id);

    List<SaleReportResponse> getSaleForReport();

    List<SaleReportResponse> getSaleBetweendatesForReport(LocalDate start, LocalDate end);
}
