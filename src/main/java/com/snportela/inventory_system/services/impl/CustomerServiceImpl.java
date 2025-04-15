package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.Customer;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.CustomerRepository;
import com.snportela.inventory_system.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findOne(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Customer update(UUID customerId, Customer customer) {

        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(NotFoundException::new);

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void delete(UUID customerId) {
        customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
        customerRepository.deleteById(customerId);
    }
}
