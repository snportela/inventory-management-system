package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.SupplierAdressDto;
import com.snportela.inventory_system.domain.entities.SupplierAdress;
import com.snportela.inventory_system.mappers.SupplierAdressMapper;
import com.snportela.inventory_system.services.SupplierAdressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supplier-adresses")
public class SupplierAdressController {

    private final SupplierAdressService supplierAdressService;

    private final SupplierAdressMapper supplierAdressMapper;

    public SupplierAdressController(SupplierAdressService supplierAdressService, SupplierAdressMapper supplierAdressMapper) {
        this.supplierAdressService = supplierAdressService;
        this.supplierAdressMapper = supplierAdressMapper;
    }

    @GetMapping
    public ResponseEntity<List<SupplierAdressDto>> listSupplierAdresses() {
        List<SupplierAdressDto> supplierAdressList = supplierAdressService.findAll().stream().map(supplierAdressMapper::supplierAdressToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(supplierAdressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierAdressDto> getSupplierAdress(@PathVariable("id") UUID supplierAdressId ) {
        SupplierAdress foundSupplierAdress = supplierAdressService.findOne(supplierAdressId);
        return ResponseEntity.status(HttpStatus.FOUND).body(supplierAdressMapper.supplierAdressToDto(foundSupplierAdress));
    }

    @PostMapping
    public ResponseEntity<SupplierAdressDto> createSupplierAdress(@RequestBody SupplierAdressDto supplierAdressDto) {
        SupplierAdress savedSupplierAdress = supplierAdressService.save(supplierAdressMapper.dtoToSupplierAdress(supplierAdressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierAdressMapper.supplierAdressToDto(savedSupplierAdress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierAdressDto> updateSupplierAdress(
            @PathVariable("id") UUID supplierAdressId, @RequestBody SupplierAdressDto supplierAdressDto
    ) {
        SupplierAdress updatedSupplierAdress = supplierAdressService.update(supplierAdressId, supplierAdressMapper.dtoToSupplierAdress(supplierAdressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierAdressMapper.supplierAdressToDto(updatedSupplierAdress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplierAdress(@PathVariable("id") UUID supplierAdressId) {
        supplierAdressService.delete(supplierAdressId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted supplier adress with ID: " + supplierAdressId);
    }
}
