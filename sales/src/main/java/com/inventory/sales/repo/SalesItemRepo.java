package com.inventory.sales.repo;

import com.inventory.sales.dto.SalesItemsResponse;
import com.inventory.sales.model.Sale;
import com.inventory.sales.model.SalesItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesItemRepo extends JpaRepository<SalesItems,Long>
{

    List<SalesItems> findBySale(Sale s);
}
