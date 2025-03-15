package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    CustomerEntity save(CustomerEntity customerEntity);

    List<CustomerEntity> findAll();

    Optional<CustomerEntity> findOne(UUID customerId);

    boolean isExists(UUID customerId);

    void delete(UUID customerId);
}
