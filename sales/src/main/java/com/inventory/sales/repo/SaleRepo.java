package com.inventory.sales.repo;

import com.inventory.sales.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale,Long>
{
    List<Sale> findBySaleDateBetween(LocalDate start, LocalDate end);
}
