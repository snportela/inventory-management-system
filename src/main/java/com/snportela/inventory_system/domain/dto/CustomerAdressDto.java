package com.snportela.inventory_system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAdressDto {

    private UUID customerAdressId;

    private CustomerDto customer;

    private String street;

    private String district;

    private Integer number;

    private String city;

    private String state;

    private String postalCode;

    private String details;

    private String receiverName;
}
