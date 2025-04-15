package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {

    Customer save(Customer customer);

    Page<Customer> findAll(Pageable pageable);

    Customer findOne(UUID customerId);

    Customer update(UUID customerId, Customer customer);

    void delete(UUID customerId);
}
