package com.snportela.inventory_system.domain.dto;

import java.util.UUID;

public record CustomerDto(
        UUID customerId,
        String name,
        String phone,
        String email
) { }

