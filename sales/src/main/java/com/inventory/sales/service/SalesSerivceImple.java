package com.inventory.sales.service;

import com.inventory.sales.dto.SaleRequest;
import com.inventory.sales.dto.SaleResponse;
import com.inventory.sales.dto.SalesItemRequest;
import com.inventory.sales.dto.SalesItemsResponse;
import com.inventory.sales.exception.ResourceNotFoundException;
import com.inventory.sales.model.Sale;
import com.inventory.sales.model.SalesItems;
import com.inventory.sales.repo.SaleRepo;
import com.inventory.sales.repo.SalesItemRepo;
import com.inventory.sharedfiles.SaleReportResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesSerivceImple implements SalesSerivce{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FromProduct fromProduct;

    @Autowired
    SaleRepo saleRepo;

    @Autowired
    SalesItemRepo salesItemRepo;

    @Override
    public SaleResponse add(SaleRequest request) {
        Sale sale = new Sale();
        sale.setSaleDate(LocalDate.now());
        sale.setCustomerUsername(request.getCustomerUsername());



        List<SalesItemRequest> salesItemRequests = request.getItem();
        List<SalesItems> salesItems = salesItemRequests.stream()
                .map(salesItemRequest ->{
                    SalesItems s = modelMapper.map(salesItemRequest,SalesItems.class);
                    s.setId(null);
                    s.setSale(sale);
                    return s;
                }).toList();

        sale.setSalesItemsList(salesItems);

        Long totalQuantity = 0L;
        Double totalPrice = 0.;
        List<Long> list = new ArrayList<>();
        for(SalesItems items:salesItems)
        {
            if(fromProduct.updateProductBySale(items.getProductId(), items.getQuantity()))
            {
                totalQuantity+= items.getQuantity();
                totalPrice+= fromProduct.productPrice(items.getProductId())* items.getQuantity();
            }
            else {
                list.add(items.getProductId());
            }
        }

        sale.setTotalPrice(totalPrice);
        sale.setTotalQuantity(totalQuantity);
        Sale saleSaved = saleRepo.save(sale);

        SaleResponse sr = modelMapper.map(saleSaved,SaleResponse.class);
        List<SalesItemsResponse> salesItemsResponse = salesItems.stream()
                .map(item -> modelMapper.map(item,SalesItemsResponse.class))
                .toList();
        sr.setSalesItemsResponses(salesItemsResponse);

//        Map<String, Object> map = new HashMap<>();
//        map.put("slaes product",sr);
//        map.put("Not slaes","A")


        return sr;
    }

    @Override
    public List<SaleResponse> getAll() {
        List<Sale> sale = saleRepo.findAll();
        List<SaleResponse> saleResponses = sale.stream()
                .map(s->
                {
                    SaleResponse sr = modelMapper.map(s,SaleResponse.class);
                    List<SalesItems> st = salesItemRepo.findBySale(s);
                    List<SalesItemsResponse> str = st.stream()
                            .map(e -> modelMapper.map(e,SalesItemsResponse.class))
                            .toList();
                    sr.setSalesItemsResponses(str);
                    return sr;
                }).toList();
        return saleResponses;
    }

    @Override
    public SaleResponse getById(Long id) {
        Sale sale = saleRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Sale",id));
        List<SalesItems> si = salesItemRepo.findBySale(sale);
        List<SalesItemsResponse> str = si.stream()
                .map(e -> modelMapper.map(e,SalesItemsResponse.class))
                .toList();
        SaleResponse sr = modelMapper.map(sale,SaleResponse.class);
        sr.setSalesItemsResponses(str);
        return sr;
    }

    @Override
    public List<SaleReportResponse> getSaleForReport() {
        List<Sale> sale = saleRepo.findAll();
        List<SaleReportResponse> r = sale.stream()
                .map(s->modelMapper.map(s,SaleReportResponse.class))
                .toList();
        return r;
    }

    @Override
    public List<SaleReportResponse> getSaleBetweendatesForReport(LocalDate start, LocalDate end) {
        List<Sale> sale = saleRepo.findBySaleDateBetween(start,end);
        List<SaleReportResponse> r = sale.stream()
                .map(s->modelMapper.map(s,SaleReportResponse.class))
                .toList();
        return r;
    }
}
