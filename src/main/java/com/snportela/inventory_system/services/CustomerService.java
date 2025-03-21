package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findOne(UUID customerId);

    Customer update(UUID customerId, Customer customer);

    void delete(UUID customerId);
}
