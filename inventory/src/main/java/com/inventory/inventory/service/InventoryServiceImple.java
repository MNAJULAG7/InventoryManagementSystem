package com.inventory.inventory.service;

import com.inventory.inventory.dto.InventoryReposne;
import com.inventory.inventory.dto.ProductStocks;
import com.inventory.sharedfiles.ProductResponse;
import com.inventory.sharedfiles.ProductResponsePage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImple implements InventoryService{
    @Autowired
    FromProductService productService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public InventoryReposne getAvailablestocks(Long pageNumber, Long pageSize, String sortBy, String sortDir) {

        ProductResponsePage responsePage = productService.getAvailablestocks(pageNumber,pageSize,sortBy,sortDir).getBody();
        List<ProductStocks> productStocks = responsePage.getProductResponse()
                .stream()
                .map(product->modelMapper.map(product,ProductStocks.class))
                .toList();
        InventoryReposne inventoryReposne = new InventoryReposne();
        inventoryReposne.setProductStocks(productStocks);
        inventoryReposne.setPageNumber(responsePage.getPageNumber());
        inventoryReposne.setPageSize(responsePage.getPageSize());
        inventoryReposne.setTotalPages(responsePage.getTotalPages());
        inventoryReposne.setTotalElements(responsePage.getTotalElements());
        inventoryReposne.setLastPage(responsePage.isLastPage());

       return inventoryReposne;
    }

    @Override
    public ProductStocks getStockById(Long id) {
        ProductResponse productResponse = productService.getallById(id).getBody();
        ProductStocks p = modelMapper.map(productResponse,ProductStocks.class);
        return p;

    }

    @Override
    public ProductStocks updateStockById(Long id, Long quantity) {
        productService.updateQuantityOfProduct(id,quantity);
        return getStockById(id);
    }
}
