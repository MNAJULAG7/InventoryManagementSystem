package com.inventory.purchase.service;

import com.inventory.purchase.dto.PurchaseRequest;
import com.inventory.sharedfiles.PurchaseResponse;

import java.util.List;

public interface PurchaseService {
    PurchaseResponse add(PurchaseRequest request);

    PurchaseResponse getById(Long id);

    List<PurchaseResponse> getAll();
}
