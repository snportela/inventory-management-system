package com.snportela.inventory_system.exceptions;

public record ErrorResponse(
        int status,
        String message,
        String details
) { }