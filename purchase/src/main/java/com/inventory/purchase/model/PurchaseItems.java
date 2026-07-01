package com.inventory.purchase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long quantity;
    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

}
