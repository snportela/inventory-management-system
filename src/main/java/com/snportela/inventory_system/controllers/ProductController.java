package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.ProductDto;
import com.snportela.inventory_system.domain.entities.Product;
import com.snportela.inventory_system.mappers.ProductMapper;
import com.snportela.inventory_system.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<ProductDto> productList = productService.findAll().stream().map(productMapper::productToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") UUID productId) {
        Product foundProduct = productService.findOne(productId);
        return ResponseEntity.status(HttpStatus.FOUND).body(productMapper.productToDto(foundProduct));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product savedProduct = productService.save(productMapper.dtoToProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToDto(savedProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable("id") UUID productId, @RequestBody ProductDto productDto
    ) {
        Product updatedProduct = productService.update(productId, productMapper.dtoToProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.productToDto(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID productId) {
        productService.delete(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted product with ID: " + productId);
    }

}
