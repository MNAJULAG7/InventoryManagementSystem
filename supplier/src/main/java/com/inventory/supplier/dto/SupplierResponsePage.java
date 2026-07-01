package com.inventory.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponsePage {
    private List<SupplierResponse> supplierResponses;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private boolean isLastPage;
}
