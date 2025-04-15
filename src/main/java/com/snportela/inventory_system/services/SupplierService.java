package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SupplierService {

    Supplier save(Supplier supplier);

    Page<Supplier> findAll(Pageable pageable);

    Supplier findOne(UUID supplierId);

    Supplier update(UUID supplierId, Supplier supplier);

    void delete(UUID supplierId);
}
