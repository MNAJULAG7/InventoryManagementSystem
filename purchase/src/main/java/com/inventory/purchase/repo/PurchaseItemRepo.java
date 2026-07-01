package com.inventory.purchase.repo;

import com.inventory.purchase.model.PurchaseItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepo extends JpaRepository<PurchaseItems,Long>
{
    List<PurchaseItems> findAllByPurchaseId(Long id);
}
