package com.snportela.inventory_system.domain;

import lombok.Getter;

@Getter
public enum TransferType {

    IN("In"),
    OUT("Out"),
    LOSS("Loss");

    private final String TransferType;

    TransferType(String transferType) {
        this.TransferType = transferType;
    }
}
