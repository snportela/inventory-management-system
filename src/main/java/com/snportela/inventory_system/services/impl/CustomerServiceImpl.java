package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.CustomerEntity;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.CustomerRepository;
import com.snportela.inventory_system.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

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
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity findOne(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
    }

    @Override
    public CustomerEntity update(UUID customerId, CustomerEntity customerEntity) {

        CustomerEntity existingCustomer = customerRepository.findById(customerId).orElseThrow(NotFoundException::new);

        existingCustomer.setName(customerEntity.getName());
        existingCustomer.setPhone(customerEntity.getPhone());
        existingCustomer.setEmail(customerEntity.getEmail());

        return customerRepository.save(existingCustomer);

    }

    @Override
    public void delete(UUID customerId) {
        customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
        customerRepository.deleteById(customerId);
    }
}
