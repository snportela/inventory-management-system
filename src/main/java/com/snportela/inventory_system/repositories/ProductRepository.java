package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.Product;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM products WHERE deleted_at IS NULL", nativeQuery = true)
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM products WHERE product_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<Product> findById(UUID productId);

    @Modifying
    @Query(value = "UPDATE products SET deleted_at = NOW() WHERE product_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID productId);
}
