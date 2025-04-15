package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.CustomerAddressDto;
import com.snportela.inventory_system.domain.CustomerAddress;
import com.snportela.inventory_system.mappers.CustomerAddressMapper;
import com.snportela.inventory_system.services.CustomerAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer-addresses")
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    private final CustomerAddressMapper customerAddressMapper;

    public CustomerAddressController(CustomerAddressService customerAddressService, CustomerAddressMapper customerAddressMapper) {
        this.customerAddressService = customerAddressService;
        this.customerAddressMapper = customerAddressMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerAddressDto>> listCustomerAddresses(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerAddress> customerAddressList = customerAddressService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(customerAddressList.stream().map(customerAddressMapper::customerAddressToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddressDto> getCustomerAddress(@PathVariable("id") UUID customerAddressId) {
        CustomerAddress foundCustomerAddress = customerAddressService.findOne(customerAddressId);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerAddressMapper.customerAddressToDto(foundCustomerAddress));
    }

    @PostMapping
    public ResponseEntity<CustomerAddressDto> createCustomerAddress(@RequestBody CustomerAddressDto customerAddressDto) {
        CustomerAddress savedCustomerAddress = customerAddressService.save(customerAddressMapper.dtoToCustomerAddress(customerAddressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAddressMapper.customerAddressToDto(savedCustomerAddress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddressDto> updateCustomerAddress(
            @PathVariable("id") UUID customerAddressId,
            @RequestBody CustomerAddressDto customerAddressDto
    ) {
        CustomerAddress updatedCustomerAddress = customerAddressService.update(customerAddressId, customerAddressMapper.dtoToCustomerAddress(customerAddressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAddressMapper.customerAddressToDto(updatedCustomerAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerAddress(@PathVariable("id") UUID customerAddressId) {
        customerAddressService.delete(customerAddressId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted address with ID: " + customerAddressId);
    }

}
