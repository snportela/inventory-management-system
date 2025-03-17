package com.snportela.inventory_system.exceptions;

public class NotFoundException extends BusinessException {

    public NotFoundException() { super("Resource not found."); }

}