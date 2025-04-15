package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.SupplierAddress;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.SupplierAddressRepository;
import com.snportela.inventory_system.services.SupplierAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierAddressServiceImpl implements SupplierAddressService {

    private final SupplierAddressRepository supplierAddressRepository;

    public SupplierAddressServiceImpl(SupplierAddressRepository supplierAddressRepository) {
        this.supplierAddressRepository = supplierAddressRepository;
    }

    @Override
    public SupplierAddress save(SupplierAddress supplierAddress) {
        return supplierAddressRepository.save(supplierAddress);
    }

    @Override
    public Page<SupplierAddress> findAll(Pageable pageable) {
        return supplierAddressRepository.findAll(pageable);
    }

    @Override
    public SupplierAddress findOne(UUID supplierAddressId) {
        return supplierAddressRepository.findById(supplierAddressId).orElseThrow(NotFoundException::new);
    }

    @Override
    public SupplierAddress update(UUID supplierAddressId, SupplierAddress supplierAddress) {
        SupplierAddress existingSupplierAddress = supplierAddressRepository.findById(supplierAddressId).orElseThrow(NotFoundException::new);

        existingSupplierAddress.setSupplier(supplierAddress.getSupplier());
        existingSupplierAddress.setStreet(supplierAddress.getStreet());
        existingSupplierAddress.setDistrict(supplierAddress.getDistrict());
        existingSupplierAddress.setNumber(supplierAddress.getNumber());
        existingSupplierAddress.setCity(supplierAddress.getCity());
        existingSupplierAddress.setState(supplierAddress.getState());
        existingSupplierAddress.setPostalCode(supplierAddress.getPostalCode());
        existingSupplierAddress.setDetails(supplierAddress.getDetails());

        return supplierAddressRepository.save(existingSupplierAddress);

    }

    @Override
    public void delete(UUID supplierAddressId) {
        supplierAddressRepository.findById(supplierAddressId).orElseThrow(NotFoundException::new);
        supplierAddressRepository.deleteById(supplierAddressId);
    }
}
