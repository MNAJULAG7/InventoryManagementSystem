package com.inventory.sharedfiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleReportResponse {
    private Long id;
    private String customerUsername;
    private LocalDate saleDate;
    private Double totalPrice;
}
