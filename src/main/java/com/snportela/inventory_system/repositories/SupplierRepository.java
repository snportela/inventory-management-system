package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.Supplier;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    @Query(value = "SELECT * FROM suppliers WHERE deleted_at IS NULL", nativeQuery = true)
    List<Supplier> findAll();

    @Query(value = "SELECT * FROM suppliers WHERE supplier_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<Supplier> findById(UUID supplierId);

    @Modifying
    @Query(value = "UPDATE suppliers SET deleted_at = NOW() WHERE supplier_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID supplierId);
}
