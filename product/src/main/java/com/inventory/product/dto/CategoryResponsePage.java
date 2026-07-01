package com.inventory.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponsePage {

    private List<com.inventory.sharedfiles.CategoryResponse> categoryResponse;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private boolean isLastPage;
}
