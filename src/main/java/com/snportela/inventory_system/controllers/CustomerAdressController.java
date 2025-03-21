package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.CustomerAdressDto;
import com.snportela.inventory_system.domain.entities.CustomerAdress;
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
        List<CustomerAdressDto> customerAdressList = customerAdressService.findAll().stream().map(customerAdressMapper::customerAdressToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerAdressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAdressDto> getCustomerAdress(@PathVariable("id") UUID customerAdressId) {
        CustomerAdress foundCustomerAdress = customerAdressService.findOne(customerAdressId);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerAdressMapper.customerAdressToDto(foundCustomerAdress));
    }

    @PostMapping
    public ResponseEntity<CustomerAdressDto> createCustomerAdress(@RequestBody CustomerAdressDto customerAdressDto) {
        CustomerAdress savedCustomerAdress = customerAdressService.save(customerAdressMapper.dtoToCustomerAdress(customerAdressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAdressMapper.customerAdressToDto(savedCustomerAdress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAdressDto> updateCustomerAdress(
            @PathVariable("id") UUID customerAdressId,
            @RequestBody CustomerAdressDto customerAdressDto
    ) {
        CustomerAdress updatedCustomerAdress = customerAdressService.update(customerAdressId, customerAdressMapper.dtoToCustomerAdress(customerAdressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAdressMapper.customerAdressToDto(updatedCustomerAdress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerAdress(@PathVariable("id") UUID customerAdressId) {
        customerAdressService.delete(customerAdressId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted adress with ID: " + customerAdressId);
    }

}
