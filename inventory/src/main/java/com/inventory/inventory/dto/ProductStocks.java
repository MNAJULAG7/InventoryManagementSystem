package com.inventory.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStocks {
    private Long id;
    private String name;
    private Long quantity;
}
