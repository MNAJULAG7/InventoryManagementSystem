package com.inventory.report.dto;

import com.inventory.sharedfiles.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPurchaseResponse {
    private List<PurchaseResponse> purchaseResponse;
    private Double totalPurchases;
}
