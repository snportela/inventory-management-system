package com.snportela.inventory_system.domain.dto;

import java.util.UUID;

public record ProductCategoryDto(

        UUID categoryId,

        String name,

        String description
) {
}
