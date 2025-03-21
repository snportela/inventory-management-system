package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findOne(UUID productId);

    Product update(UUID productId, Product product);

    void delete(UUID productId);
}
