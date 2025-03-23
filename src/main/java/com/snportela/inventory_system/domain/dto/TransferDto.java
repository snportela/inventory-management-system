package com.snportela.inventory_system.domain.dto;

import com.snportela.inventory_system.domain.TransferType;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransferDto(

        UUID transferId,

        ProductDto product,

        Integer quantity,

        LocalDateTime transferDate,

        TransferType transferType,

        String description
) {
}
