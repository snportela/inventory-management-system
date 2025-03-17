package com.snportela.inventory_system.domain.dto;

import java.util.UUID;

public record SupplierDto(
        UUID supplierId,
        String name,
        String phone,
        String email,
        String cnpj
) {
}
