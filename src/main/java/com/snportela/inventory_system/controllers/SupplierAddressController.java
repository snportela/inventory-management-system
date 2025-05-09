package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.SupplierAddress;
import com.snportela.inventory_system.dtos.SupplierAddressDto;
import com.snportela.inventory_system.mappers.SupplierAddressMapper;
import com.snportela.inventory_system.services.SupplierAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supplier-addresses")
public class SupplierAddressController {

    private final SupplierAddressService supplierAddressService;

    private final SupplierAddressMapper supplierAddressMapper;

    public SupplierAddressController(SupplierAddressService supplierAddressService, SupplierAddressMapper supplierAddressMapper) {
        this.supplierAddressService = supplierAddressService;
        this.supplierAddressMapper = supplierAddressMapper;
    }

    @GetMapping
    public ResponseEntity<List<SupplierAddressDto>> listSupplierAddresses(@RequestParam int page, @RequestParam int size,
                                                                          @RequestParam String sortField, @RequestParam String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SupplierAddress> supplierAddressList = supplierAddressService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(supplierAddressList.stream().map(supplierAddressMapper::supplierAddressToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierAddressDto> getSupplierAddress(@PathVariable("id") UUID supplierAddressId ) {
        SupplierAddress foundSupplierAddress = supplierAddressService.findOne(supplierAddressId);
        return ResponseEntity.status(HttpStatus.FOUND).body(supplierAddressMapper.supplierAddressToDto(foundSupplierAddress));
    }

    @PostMapping
    public ResponseEntity<SupplierAddressDto> createSupplierAddress(@RequestBody SupplierAddressDto supplierAddressDto) {
        SupplierAddress savedSupplierAddress = supplierAddressService.save(supplierAddressMapper.dtoToSupplierAddress(supplierAddressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierAddressMapper.supplierAddressToDto(savedSupplierAddress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierAddressDto> updateSupplierAddress(
            @PathVariable("id") UUID supplierAddressId, @RequestBody SupplierAddressDto supplierAddressDto
    ) {
        SupplierAddress updatedSupplierAddress = supplierAddressService.update(supplierAddressId, supplierAddressMapper.dtoToSupplierAddress(supplierAddressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierAddressMapper.supplierAddressToDto(updatedSupplierAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplierAddress(@PathVariable("id") UUID supplierAddressId) {
        supplierAddressService.delete(supplierAddressId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted supplier address with ID: " + supplierAddressId);
    }
}
