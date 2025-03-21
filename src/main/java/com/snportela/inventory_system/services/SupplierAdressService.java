package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.SupplierAdress;

import java.util.List;
import java.util.UUID;

public interface SupplierAdressService {

    SupplierAdress save(SupplierAdress supplierAdress);

    List<SupplierAdress> findAll();

    SupplierAdress findOne(UUID supplierAdressId);

    SupplierAdress update(UUID supplierAdressId, SupplierAdress supplierAdress);

    void delete(UUID supplierAdressId);
}
