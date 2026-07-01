package com.inventory.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemRequest {
    private Long productId;
    private Long quantity;
    private Double productPrice;
}
