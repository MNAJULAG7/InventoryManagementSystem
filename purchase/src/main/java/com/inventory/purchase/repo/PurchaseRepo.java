package com.inventory.purchase.repo;

import com.inventory.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Long>
{
}
