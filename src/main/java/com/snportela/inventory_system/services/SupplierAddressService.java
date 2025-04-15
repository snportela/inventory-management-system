package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.SupplierAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SupplierAddressService {

    SupplierAddress save(SupplierAddress supplierAddress);

    Page<SupplierAddress> findAll(Pageable pageable);

    SupplierAddress findOne(UUID supplierAddressId);

    SupplierAddress update(UUID supplierAddressId, SupplierAddress supplierAddress);

    void delete(UUID supplierAddressId);
}
