package com.inventory.sales.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerUsername;
    private LocalDate saleDate;
    private Long totalQuantity;
    private Double totalPrice;

    @OneToMany(mappedBy = "sale",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SalesItems> salesItemsList;



}
