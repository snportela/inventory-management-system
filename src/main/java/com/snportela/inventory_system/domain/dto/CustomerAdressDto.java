package com.snportela.inventory_system.domain.dto;

import java.util.UUID;

public record CustomerAdressDto (
        UUID customerAdressId,

        CustomerDto customer,

        String street,

        String district,

        Integer number,

        String city,

        String state,

        String postalCode,

        String details,

        String receiverName

) {
}
