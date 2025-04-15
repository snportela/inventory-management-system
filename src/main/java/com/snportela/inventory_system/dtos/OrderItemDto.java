package com.snportela.inventory_system.dtos;

public record OrderItemDto(

        OrderItemIdDto orderItemId,

        Integer orderQuantity
) {
}
