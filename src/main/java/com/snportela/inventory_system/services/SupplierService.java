package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierService {

    Supplier save(Supplier supplier);

    List<Supplier> findAll();

    Supplier findOne(UUID supplierId);

    Supplier update(UUID supplierId, Supplier supplier);

    void delete(UUID supplierId);
}
