package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.ProductCategoryDto;
import com.snportela.inventory_system.domain.ProductCategory;
import com.snportela.inventory_system.mappers.ProductCategoryMapper;
import com.snportela.inventory_system.services.ProductCategoryService;
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
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> listCategories(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String order){
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductCategory> categoryDtoList = productCategoryService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoList.stream().map(productCategoryMapper::productCategoryToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getCategory(@PathVariable("id") UUID categoryId) {
        ProductCategory foundCategory = productCategoryService.findOne(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryMapper.productCategoryToDto(foundCategory));
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
