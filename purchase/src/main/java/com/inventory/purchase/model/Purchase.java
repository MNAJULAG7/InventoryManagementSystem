package com.inventory.purchase.model;

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
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supplierId;
    private LocalDate purchaseDate;
    private Long quantity;
    private Double totalPrice;

    @OneToMany(mappedBy = "purchase",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PurchaseItems> purchaseItemsList;



}
