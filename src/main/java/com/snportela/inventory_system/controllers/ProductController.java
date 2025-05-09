package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.ProductDto;
import com.snportela.inventory_system.domain.Product;
import com.snportela.inventory_system.mappers.ProductMapper;
import com.snportela.inventory_system.services.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts(@RequestParam int page, @RequestParam int size,
                                                         @RequestParam String sortField, @RequestParam String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productList = productService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productList.stream().map(productMapper::productToDto).collect(Collectors.toList()));
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
