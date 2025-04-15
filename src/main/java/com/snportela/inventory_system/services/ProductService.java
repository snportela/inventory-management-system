package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);

    Product findOne(UUID productId);

    Product update(UUID productId, Product product);

    void delete(UUID productId);
}
