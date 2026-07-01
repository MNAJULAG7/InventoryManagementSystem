package com.inventory.supplier.dto;

import com.inventory.supplier.model.Address;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {
    private Long id;
    private String name;
    private Long phone;
    private String email;
    private Address address;
}
