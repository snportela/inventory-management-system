package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.Supplier;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.SupplierRepository;
import com.snportela.inventory_system.services.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier findOne(UUID supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Supplier update(UUID supplierId, Supplier supplier) {
        Supplier existingSupplier = supplierRepository.findById(supplierId).orElseThrow(NotFoundException::new);

        existingSupplier.setName(supplier.getName());
        existingSupplier.setPhone(supplier.getPhone());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setCnpj(supplier.getCnpj());

        return supplierRepository.save(existingSupplier);
    }

    @Override
    public void delete(UUID supplierId) {
        supplierRepository.findById(supplierId).orElseThrow(NotFoundException::new);
        supplierRepository.deleteById(supplierId);
    }
}
