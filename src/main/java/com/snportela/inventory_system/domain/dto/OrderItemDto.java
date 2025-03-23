package com.snportela.inventory_system.domain.dto;

public record OrderItemDto(

        OrderItemIdDto orderItemId,

        Integer orderQuantity
) {
}
