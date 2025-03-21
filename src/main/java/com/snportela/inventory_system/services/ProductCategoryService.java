package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findAll();

    ProductCategory findOne(UUID categoryId);

    ProductCategory update(UUID categoryId, ProductCategory productCategory);

    void delete(UUID categoryId);
}
