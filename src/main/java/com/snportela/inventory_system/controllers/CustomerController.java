package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.CustomerDto;
import com.snportela.inventory_system.domain.entities.CustomerEntity;
import com.snportela.inventory_system.mappers.CustomerMapper;
import com.snportela.inventory_system.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listCustomers(){
        List<CustomerDto> customersList = customerService.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") UUID customerId) {
        CustomerEntity foundCustomer = customerService.findOne(customerId);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerMapper.toDto(foundCustomer));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerEntity savedCustomer = customerService.save(customerMapper.fromDto(customerDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toDto(savedCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable("id") UUID customerId, @RequestBody CustomerDto customerDto) {
            CustomerEntity updatedCustomer = customerService.update(customerId, customerMapper.fromDto(customerDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toDto(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") UUID customerId) {
        customerService.delete(customerId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted customer with ID: " + customerId);
    }

}
