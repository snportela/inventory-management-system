package com.snportela.inventory_system.domain.dto;

public record OrderItemIdDto (

        OrderDto order,

        ProductDto product

) {
}
