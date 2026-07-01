package com.inventory.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private String name;
    private Long phone;
    private String email;
    private Integer houseNo;
    private String street;
    private String city;
    private String district;
    private String state;
    private Long pinNumber;

}
