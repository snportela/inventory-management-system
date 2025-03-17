package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.CustomerAdressRepository;
import com.snportela.inventory_system.services.CustomerAdressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerAdressServiceImpl implements CustomerAdressService {

    private final CustomerAdressRepository customerAdressRepository;

    public CustomerAdressServiceImpl(CustomerAdressRepository customerAdressRepository) {
        this.customerAdressRepository = customerAdressRepository;
    }

    @Override
    public CustomerAdressEntity save(CustomerAdressEntity customerAdressEntity) {
        return customerAdressRepository.save(customerAdressEntity);
    }

    @Override
    public List<CustomerAdressEntity> findAll() {
        return customerAdressRepository.findAll();
    }

    @Override
    public CustomerAdressEntity findOne(UUID customerAdressId) {
        return customerAdressRepository.findById(customerAdressId).orElseThrow(NotFoundException::new);
    }

    @Override
    public CustomerAdressEntity update(UUID customerAdressId, CustomerAdressEntity customerAdressEntity) {
        CustomerAdressEntity existingAdress = customerAdressRepository.findById(customerAdressId).orElseThrow(NotFoundException::new);

        existingAdress.setCustomer(customerAdressEntity.getCustomer());
        existingAdress.setStreet(customerAdressEntity.getStreet());
        existingAdress.setDistrict(customerAdressEntity.getDistrict());
        existingAdress.setNumber(customerAdressEntity.getNumber());
        existingAdress.setCity(customerAdressEntity.getCity());
        existingAdress.setState(customerAdressEntity.getState());
        existingAdress.setPostalCode(customerAdressEntity.getPostalCode());
        existingAdress.setReceiverName(customerAdressEntity.getReceiverName());

        return customerAdressRepository.save(existingAdress);
    }

    @Override
    public void delete(UUID customerAdressId) {
        customerAdressRepository.findById(customerAdressId).orElseThrow(NotFoundException::new);
        customerAdressRepository.deleteById(customerAdressId);
    }
}
