package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.ProductCategory;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

    @Query(value = "SELECT * FROM product_categories WHERE deleted_at IS NULL", nativeQuery = true)
    Page<ProductCategory> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM product_categories WHERE category_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<ProductCategory> findById(UUID categoryId);

    @Modifying
    @Query(value = "UPDATE product_categories SET deleted_at = NOW() WHERE category_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID categoryId);
}
