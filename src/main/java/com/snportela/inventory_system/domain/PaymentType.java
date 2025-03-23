package com.snportela.inventory_system.domain;

import lombok.Getter;

@Getter
public enum PaymentType {

    CREDIT_CARD("Credit card"),
    DEBIT_CARD("Debit card"),
    CASH("Cash"),
    BANK_SLIP("Bank slip"),
    TRANSFER("Transfer");

    private final String PaymentType;

    PaymentType(String paymentType) {
        this.PaymentType = paymentType;
    }
}
