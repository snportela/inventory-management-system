package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.CustomerEntity;
import com.snportela.inventory_system.repositories.CustomerRepository;
import com.snportela.inventory_system.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return (List<CustomerEntity>) customerRepository.findAll();
    }

    @Override
    public Optional<CustomerEntity> findOne(UUID customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public boolean isExists(UUID customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public void delete(UUID customerId) {
        customerRepository.deleteById(customerId);
    }
}
