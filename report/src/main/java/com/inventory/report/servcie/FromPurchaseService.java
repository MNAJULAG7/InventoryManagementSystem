package com.inventory.report.servcie;

import com.inventory.sharedfiles.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "PURCHASE",
        path = "/api/purchases"
)
public interface FromPurchaseService
{
    @GetMapping("/getall")
    ResponseEntity<List<PurchaseResponse>> getAll();
}
