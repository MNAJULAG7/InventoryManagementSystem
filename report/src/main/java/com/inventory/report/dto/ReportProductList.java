package com.inventory.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportProductList {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long quantity;
    private Double totalStockPrice;
}
