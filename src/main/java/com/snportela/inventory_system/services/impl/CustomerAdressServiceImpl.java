package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;
import com.snportela.inventory_system.repositories.CustomerAdressRepository;
import com.snportela.inventory_system.repositories.CustomerRepository;
import com.snportela.inventory_system.services.CustomerAdressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerAdressServiceImpl implements CustomerAdressService {

    private final CustomerAdressRepository customerAdressRepository;
    private final CustomerRepository customerRepository;

    public CustomerAdressServiceImpl(CustomerAdressRepository customerAdressRepository, CustomerRepository customerRepository) {
        this.customerAdressRepository = customerAdressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerAdressEntity save(CustomerAdressEntity customerAdressEntity) {
        return customerAdressRepository.save(customerAdressEntity);
    }

    @Override
    public List<CustomerAdressEntity> findAll() {
        return (List<CustomerAdressEntity>) customerAdressRepository.findAll();
    }

    @Override
    public Optional<CustomerAdressEntity> findOne(UUID customerAdressId) {
        return customerAdressRepository.findById(customerAdressId);
    }

    @Override
    public boolean isExists(UUID customerAdressId) {
        return customerAdressRepository.existsById(customerAdressId);
    }

    @Override
    public void delete(UUID customerAdressId) {
        customerRepository.deleteById(customerAdressId);
    }
}
