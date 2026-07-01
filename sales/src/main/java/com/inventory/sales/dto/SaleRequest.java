package com.inventory.sales.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {
    private String customerUsername;
    private List<SalesItemRequest> item;
}
