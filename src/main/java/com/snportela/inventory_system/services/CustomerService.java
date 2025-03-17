package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.CustomerEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerEntity save(CustomerEntity customerEntity);

    List<CustomerEntity> findAll();

    CustomerEntity findOne(UUID customerId);

    CustomerEntity update(UUID customerId, CustomerEntity customerEntity);

    void delete(UUID customerId);
}
