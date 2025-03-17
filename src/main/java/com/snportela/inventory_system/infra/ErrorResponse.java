package com.snportela.inventory_system.infra;

public record ErrorResponse(
        int status,
        String message,
        String details
) { }
