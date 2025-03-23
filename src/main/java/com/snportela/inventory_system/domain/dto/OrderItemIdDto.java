package com.snportela.inventory_system.domain.dto;

import java.util.UUID;

public record OrderItemIdDto (

        UUID orderId,

        UUID productId

) {
}
