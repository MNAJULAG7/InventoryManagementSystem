package com.inventory.supplier.service;

import com.inventory.supplier.dto.SupplierRequest;
import com.inventory.supplier.dto.SupplierResponse;
import com.inventory.supplier.dto.SupplierResponsePage;

import java.util.List;

public interface SupplierService {

    SupplierResponse getById(Long id);

    SupplierResponse add(SupplierRequest request);

    SupplierResponse update(Long id, SupplierRequest request);

    SupplierResponse delete(Long id);

    void addall(List<SupplierRequest> request);

    SupplierResponsePage getAll(Long pageNumber, Long pageSize, String sortBy, String sortDir);
}
