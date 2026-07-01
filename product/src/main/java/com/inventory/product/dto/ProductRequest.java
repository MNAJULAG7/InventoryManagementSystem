package com.inventory.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Long quantity;
    private String category;
}
