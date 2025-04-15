package com.snportela.inventory_system.dtos;

import java.util.UUID;

public record CustomerAddressDto(
        UUID customerAddressId,

        CustomerDto customer,

        String street,

        String district,

        Integer number,

        String city,

        String state,

        String postalCode,

        String details,

        String receiverName

) { }
