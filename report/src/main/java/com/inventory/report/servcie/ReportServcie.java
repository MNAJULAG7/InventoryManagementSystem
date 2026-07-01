package com.inventory.report.servcie;

import com.inventory.report.dto.ReportProductPage;

public interface ReportServcie {
    ReportProductPage getStocks(Long pageNumber, Long pageSize, String sortBy, String sortDir);
}
