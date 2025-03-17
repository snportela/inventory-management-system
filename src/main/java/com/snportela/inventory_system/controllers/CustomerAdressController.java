package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.CustomerAdressDto;
import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;
import com.snportela.inventory_system.mappers.CustomerAdressMapper;
import com.snportela.inventory_system.services.CustomerAdressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer-adresses")
public class CustomerAdressController {

    private final CustomerAdressService customerAdressService;
    private final CustomerAdressMapper customerAdressMapper;

    public CustomerAdressController(CustomerAdressService customerAdressService, CustomerAdressMapper customerAdressMapper) {
        this.customerAdressService = customerAdressService;
        this.customerAdressMapper = customerAdressMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerAdressDto>> listCustomerAdresses() {
        List<CustomerAdressDto> customerAdressList = customerAdressService.findAll().stream().map(customerAdressMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerAdressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAdressDto> getCustomerAdress(@PathVariable("id") UUID customerAdressId) {
        CustomerAdressEntity foundCustomerAdress = customerAdressService.findOne(customerAdressId);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerAdressMapper.toDto(foundCustomerAdress));
    }

    @PostMapping
    public ResponseEntity<CustomerAdressDto> createCustomerAdress(@RequestBody CustomerAdressDto customerAdressDto) {
        CustomerAdressEntity savedCustomerAdress = customerAdressService.save(customerAdressMapper.fromDto(customerAdressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAdressMapper.toDto(savedCustomerAdress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAdressDto> updateCustomerAdress(
            @PathVariable("id") UUID customerAdressId,
            @RequestBody CustomerAdressDto customerAdressDto
    ) {
        CustomerAdressEntity updatedCustomerAdress = customerAdressService.update(customerAdressId, customerAdressMapper.fromDto(customerAdressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAdressMapper.toDto(updatedCustomerAdress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerAdress(@PathVariable("id") UUID customerAdressId) {
        customerAdressService.delete(customerAdressId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted adress with ID: " + customerAdressId);
    }

}
