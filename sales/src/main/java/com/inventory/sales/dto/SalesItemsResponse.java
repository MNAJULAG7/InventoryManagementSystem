package com.inventory.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesItemsResponse {
    private Long id;
    private Long productId;
    private Long quantity;
}
