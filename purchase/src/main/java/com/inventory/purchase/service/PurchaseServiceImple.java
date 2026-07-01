package com.inventory.purchase.service;

import com.inventory.purchase.dto.PurchaseItemRequest;
import com.inventory.sharedfiles.PurchaseItemsResponse;
import com.inventory.purchase.dto.PurchaseRequest;
import com.inventory.sharedfiles.PurchaseResponse;
import com.inventory.purchase.exception.ResourceNotFoundException;
import com.inventory.purchase.model.Purchase;
import com.inventory.purchase.model.PurchaseItems;
import com.inventory.purchase.repo.PurchaseItemRepo;
import com.inventory.purchase.repo.PurchaseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseServiceImple implements  PurchaseService{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PurchaseItemRepo purchaseItemRepo;

    @Autowired
    FromProduct fromProduct;

    @Autowired
    PurchaseRepo purchaseRepo;

    @Override
    public PurchaseResponse add(PurchaseRequest request) {

        Purchase purchase = new Purchase();
        purchase.setSupplierId(request.getSupplierId());
        purchase.setPurchaseDate(LocalDate.now());

        List<PurchaseItemRequest> itemRequestList = request.getItem();
        List<PurchaseItems> items = itemRequestList.stream()
                .map(item-> {
                    PurchaseItems p = new PurchaseItems();
                    p.setProductId(item.getProductId());
                    p.setQuantity(item.getQuantity());
                    p.setProductPrice(item.getProductPrice());
                    p.setPurchase(purchase);
                    return p;
                }).toList();

        Double totalPrice = 0.;
        Long totalQuantity = 0L;
        for (PurchaseItems item : items) {

            totalPrice += item.getProductPrice() * item.getQuantity();
            totalQuantity += item.getQuantity();

            fromProduct.updateProductByPurchase(
                    item.getProductId(),
                    item.getQuantity()
            );
        }
        purchase.setTotalPrice(totalPrice);
        purchase.setQuantity(totalQuantity);
        purchase.setPurchaseItemsList(items);

        Purchase ps = purchaseRepo.save(purchase);
        PurchaseResponse pr = modelMapper.map(ps,PurchaseResponse.class);
        List<PurchaseItemsResponse> pil = items.stream()
                .map(p -> modelMapper.map(p, PurchaseItemsResponse.class))
                .toList();
        pr.setPurchaseItemsResponses(pil);
        return pr;
    }

    @Override
    public PurchaseResponse getById(Long id) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Purchase",id));
        PurchaseResponse pr = modelMapper.map(purchase,PurchaseResponse.class);
        List<PurchaseItems> pt = purchaseItemRepo.findAllByPurchaseId(pr.getId());
        List<PurchaseItemsResponse> pir = pt.stream()
                .map(item -> modelMapper.map(item, PurchaseItemsResponse.class))
                .toList();
        pr.setPurchaseItemsResponses(pir);
        return pr;
    }

    @Override
    public List<PurchaseResponse> getAll() {
        List<Purchase> purchase = purchaseRepo.findAll();
        List<PurchaseResponse> prs = purchase.stream()
                .map(purchase1 -> {
                    PurchaseResponse pr = modelMapper.map(purchase1, PurchaseResponse.class);
                    List<PurchaseItems> pt = purchaseItemRepo.findAllByPurchaseId(pr.getId());
                    List<PurchaseItemsResponse> pir = pt.stream()
                            .map(item -> modelMapper.map(item, PurchaseItemsResponse.class))
                            .toList();
                    //System.out.println(purchase1.getPurchaseItemsList().toString());
                    pr.setPurchaseItemsResponses(pir);
                    return pr;

                }).toList();
        return prs;
    }
}
