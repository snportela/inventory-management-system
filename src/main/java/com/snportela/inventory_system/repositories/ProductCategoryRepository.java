package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.ProductCategory;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@NonNullApi
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

    Page<ProductCategory> findAll(Pageable pageable);

}
