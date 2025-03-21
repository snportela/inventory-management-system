package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.Customer;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.CustomerRepository;
import com.snportela.inventory_system.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Customer> findAll() {
        return customerRepository.findAll();
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
