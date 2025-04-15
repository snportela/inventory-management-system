package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.ProductCategory;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.ProductCategoryRepository;
import com.snportela.inventory_system.services.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }

    @Override
    public ProductCategory findOne(UUID categoryId) {
        return productCategoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
    }

    @Override
    public ProductCategory update(UUID categoryId, ProductCategory productCategory) {

        ProductCategory existingCategory = productCategoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);

        existingCategory.setName(productCategory.getName());
        existingCategory.setDescription(productCategory.getDescription());

        return productCategoryRepository.save(existingCategory);
    }

    @Override
    public void delete(UUID categoryId) {
        productCategoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
        productCategoryRepository.deleteById(categoryId);
    }
}
