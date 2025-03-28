package com.snportela.inventory_system.domain.dto;

import java.util.UUID;

public record SupplierAdressDto(
        
        UUID supplierAdressId,

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
