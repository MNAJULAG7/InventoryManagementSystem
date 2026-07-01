package com.inventory.sharedfiles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemsResponse {
    private Long id;
    private Long productId;
    private Long quantity;
    private Double productPrice;
}
