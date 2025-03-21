package com.snportela.inventory_system.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(

        UUID productId,

        String name,

        String description,

        BigDecimal price,

        Integer quantity,

        ProductCategoryDto productCategory,

        SupplierDto supplier

) {
}
