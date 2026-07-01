package com.inventory.sharedfiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long quantity;
    private String categoryName;

}
