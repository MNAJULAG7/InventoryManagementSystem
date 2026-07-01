package com.inventory.purchase.dto;

import com.inventory.purchase.model.PurchaseItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private Long id;
    private Long supplierId;
    private LocalDate purchaseDate;
    private Long quantity;
    private Double totalPrice;
    private List<PurchaseItemsResponse> purchaseItemsResponses;
}
