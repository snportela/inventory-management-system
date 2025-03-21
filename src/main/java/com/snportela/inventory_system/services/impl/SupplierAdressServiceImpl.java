package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.SupplierAdress;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.SupplierAdressRepository;
import com.snportela.inventory_system.services.SupplierAdressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierAdressServiceImpl implements SupplierAdressService {

    private final SupplierAdressRepository supplierAdressRepository;

    public SupplierAdressServiceImpl(SupplierAdressRepository supplierAdressRepository) {
        this.supplierAdressRepository = supplierAdressRepository;
    }

    @Override
    public SupplierAdress save(SupplierAdress supplierAdress) {
        return supplierAdressRepository.save(supplierAdress);
    }

    @Override
    public List<SupplierAdress> findAll() {
        return supplierAdressRepository.findAll();
    }

    @Override
    public SupplierAdress findOne(UUID supplierAdressId) {
        return supplierAdressRepository.findById(supplierAdressId).orElseThrow(NotFoundException::new);
    }

    @Override
    public SupplierAdress update(UUID supplierAdressId, SupplierAdress supplierAdress) {
        SupplierAdress existingSupplierAdress = supplierAdressRepository.findById(supplierAdressId).orElseThrow(NotFoundException::new);

        existingSupplierAdress.setSupplier(supplierAdress.getSupplier());
        existingSupplierAdress.setStreet(supplierAdress.getStreet());
        existingSupplierAdress.setDistrict(supplierAdress.getDistrict());
        existingSupplierAdress.setNumber(supplierAdress.getNumber());
        existingSupplierAdress.setCity(supplierAdress.getCity());
        existingSupplierAdress.setState(supplierAdress.getState());
        existingSupplierAdress.setPostalCode(supplierAdress.getPostalCode());
        existingSupplierAdress.setDetails(supplierAdress.getDetails());
        existingSupplierAdress.setReceiverName(supplierAdress.getReceiverName());

        return supplierAdressRepository.save(existingSupplierAdress);

    }

    @Override
    public void delete(UUID supplierAdressId) {
        supplierAdressRepository.findById(supplierAdressId).orElseThrow(NotFoundException::new);
        supplierAdressRepository.deleteById(supplierAdressId);
    }
}
