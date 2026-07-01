package com.inventory.product.service;

import com.inventory.product.dto.CategoryRequest;
import com.inventory.sharedfiles.CategoryResponse;
import com.inventory.product.dto.CategoryResponsePage;
import java.util.*;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);

    CategoryResponsePage getAll(Long pageNumber, Long pageSize, String sortBy, String sortDir);

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    CategoryResponse delete(Long id);

    void addall(List<CategoryRequest> request);
}
