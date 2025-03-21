package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.ProductCategoryDto;
import com.snportela.inventory_system.domain.entities.ProductCategory;
import com.snportela.inventory_system.mappers.ProductCategoryMapper;
import com.snportela.inventory_system.services.ProductCategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> listCategories() {
        List<ProductCategoryDto> categoryDtoList = productCategoryService.findAll().stream().map(productCategoryMapper::productCategoryToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getCategory(@PathVariable("id") UUID categoryId) {
        ProductCategory foundCategory = productCategoryService.findOne(categoryId);
        return ResponseEntity.status(HttpStatus.FOUND).body(productCategoryMapper.productCategoryToDto(foundCategory));
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDto> createCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        ProductCategory savedCategory = productCategoryService.save(productCategoryMapper.dtoToProductCategory(productCategoryDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryMapper.productCategoryToDto(savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> updateCategory(
            @PathVariable("id") UUID categoryId, @RequestBody ProductCategoryDto productCategoryDto) {

        ProductCategory updatedCategory = productCategoryService.update(categoryId, productCategoryMapper.dtoToProductCategory(productCategoryDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryMapper.productCategoryToDto(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") UUID categoryId) {
        productCategoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted category with ID: " + categoryId);
    }
}
