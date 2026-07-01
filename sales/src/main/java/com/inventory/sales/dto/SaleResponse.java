package com.inventory.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    private Long id;
    private String customerUsername;
    private LocalDate saleDate;
    private Long totalQuantity;
    private Double totalPrice;
    private List<SalesItemsResponse> salesItemsResponses;
}