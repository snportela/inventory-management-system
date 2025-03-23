package com.snportela.inventory_system.domain;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    AWAITING_PAYMENT("Awaiting Payment"),
    PAYMENT_RECEIVED("Payment Received"),
    PAYMENT_UPDATED("Payment updated"),
    COMPLETED("Completed"),
    REFUNDED_PARTIALLY("Refunded Partially"),
    REFUNDED("Refunded"),
    CANCELLED("Cancelled"),
    FAILED("Failed"),
    EXPIRED("Expired");


    private final String PaymentStatus;

    PaymentStatus(String paymentStatus) {
        this.PaymentStatus = paymentStatus;
    }
}
