package com.snportela.inventory_system.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PROCESSING_STOCK("Processing stock"),
    READY_FOR_PACKING("Ready for packing"),
    READY_TO_DELIVER("Ready to deliver"),
    DELIVERY_IN_PROGRESS("Delivery in progress"),
    DELIVERED("Delivered"),
    RECEIVED("Received"),
    RETURNED("Returned"),
    NOT_DELIVERED("Not delivered");

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
