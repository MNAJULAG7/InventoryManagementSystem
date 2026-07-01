package com.inventory.product.service;

import com.inventory.product.dto.ProductRequest;
import com.inventory.sharedfiles.ProductResponse;
import com.inventory.sharedfiles.ProductResponsePage;

import java.util.List;

public interface ProductService {
    ProductResponsePage getAll(Long pageNumber, Long pageSize, String sortBy, String sortDir);

    ProductResponse getById(Long id);

    ProductResponse add(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    ProductResponse delete(Long id);

    void addall(List<ProductRequest> request);

    void updateProductByPurchase(Long productId, Long quantity);

    boolean updateProductBySale(Long productId, Long quantity);

    Double productPrice(Long productId);

    ProductResponsePage getAvailablestocks(Long pageNumber, Long pageSize, String sortBy, String sortDir);

    boolean updateQuantityOfProduct(Long id,Long quantity);
}
