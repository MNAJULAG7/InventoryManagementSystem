package com.inventory.purchase.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private Long supplierId;
    private List<PurchaseItemRequest> item;
}
