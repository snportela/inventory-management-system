package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.SupplierDto;
import com.snportela.inventory_system.domain.entities.Supplier;
import com.snportela.inventory_system.mappers.SupplierMapper;
import com.snportela.inventory_system.services.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    private final SupplierMapper supplierMapper;

    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> listSuppliers() {
        List<SupplierDto> suppliersList = supplierService.findAll().stream().map(supplierMapper::supplierToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(suppliersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable("id") UUID supplierId) {
        Supplier foundSupplier = supplierService.findOne(supplierId);
        return ResponseEntity.status(HttpStatus.FOUND).body(supplierMapper.supplierToDto(foundSupplier));
    }

    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        Supplier savedSupplier = supplierService.save(supplierMapper.dtoToSupplier(supplierDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierMapper.supplierToDto(savedSupplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(
            @PathVariable("id") UUID supplierId, @RequestBody SupplierDto supplierDto
    ) {
        Supplier updatedSupplier = supplierService.update(supplierId, supplierMapper.dtoToSupplier(supplierDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierMapper.supplierToDto(updatedSupplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("id") UUID supplierId) {
        supplierService.delete(supplierId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted supplier with ID: " + supplierId);
    }

}
