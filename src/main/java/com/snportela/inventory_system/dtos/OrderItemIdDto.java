package com.snportela.inventory_system.dtos;

import java.util.UUID;

public record OrderItemIdDto (

        UUID orderId,

        UUID productId

) {
}
