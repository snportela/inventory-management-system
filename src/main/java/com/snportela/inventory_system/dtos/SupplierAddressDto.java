package com.snportela.inventory_system.dtos;

import java.util.UUID;

public record SupplierAddressDto(
        
        UUID supplierAddressId,

        SupplierDto supplier,

        String street,

        String district,

        Integer number,

        String city,

        String state,

        String postalCode,

        String details

) {
}
