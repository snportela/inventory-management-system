package com.snportela.inventory_system.dtos;

import java.util.UUID;

public record ProductCategoryDto(

        UUID categoryId,

        String name,

        String description
) {
}
