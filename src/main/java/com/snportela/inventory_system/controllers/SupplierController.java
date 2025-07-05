package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.SupplierDto;
import com.snportela.inventory_system.domain.Supplier;
import com.snportela.inventory_system.mappers.SupplierMapper;
import com.snportela.inventory_system.services.SupplierService;
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
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    private final SupplierMapper supplierMapper;

    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @GetMapping
    public ResponseEntity<List<SupplierDto>> listSuppliers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String order){
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Supplier> suppliersList = supplierService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(suppliersList.stream().map(supplierMapper::supplierToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable("id") UUID supplierId) {
        Supplier foundSupplier = supplierService.findOne(supplierId);
        return ResponseEntity.status(HttpStatus.OK).body(supplierMapper.supplierToDto(foundSupplier));
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
