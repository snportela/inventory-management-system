package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    Page<ProductCategory> findAll(Pageable pageable);

    ProductCategory findOne(UUID categoryId);

    ProductCategory update(UUID categoryId, ProductCategory productCategory);

    void delete(UUID categoryId);
}
