package com.inventory.sharedfiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponsePage {
    private List<ProductResponse> productResponse;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private boolean isLastPage;

}
