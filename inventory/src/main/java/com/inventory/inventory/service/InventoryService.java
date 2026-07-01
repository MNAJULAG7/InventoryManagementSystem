package com.inventory.inventory.service;

import com.inventory.inventory.dto.InventoryReposne;
import com.inventory.inventory.dto.ProductStocks;
import com.inventory.sharedfiles.ProductResponsePage;

public interface InventoryService {


    InventoryReposne getAvailablestocks(Long pageNumber, Long pageSize, String sortBy, String sortDir);

    ProductStocks getStockById(Long id);

    ProductStocks updateStockById(Long id, Long quantity);
}
