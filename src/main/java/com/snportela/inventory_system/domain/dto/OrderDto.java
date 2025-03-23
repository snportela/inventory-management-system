package com.snportela.inventory_system.domain.dto;

import com.snportela.inventory_system.domain.OrderStatus;
import com.snportela.inventory_system.domain.PaymentStatus;
import com.snportela.inventory_system.domain.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(

        UUID orderId,

        CustomerDto customer,

        CustomerAdressDto customerAdress,

        OrderStatus orderStatus,

        LocalDateTime expectedDate,

        LocalDateTime actualDate,

        PaymentType paymentType,

        PaymentStatus paymentStatus,

        BigDecimal totalPrice,

        String trackingNumber
) {
}
