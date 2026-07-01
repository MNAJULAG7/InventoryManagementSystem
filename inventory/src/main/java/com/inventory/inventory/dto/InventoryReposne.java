package com.inventory.inventory.dto;

import com.inventory.sharedfiles.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReposne {
    private List<ProductStocks> productStocks;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private boolean isLastPage;
}
