package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.SupplierAddress;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface SupplierAddressRepository extends JpaRepository<SupplierAddress, UUID> {

    @Query(value = "SELECT * FROM suppliers_adresses WHERE deleted_at IS NULL", nativeQuery = true)
    Page<SupplierAddress> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM suppliers_adresses WHERE supplier_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<SupplierAddress> findById(UUID supplierAddressId);

    @Modifying
    @Query(value = "UPDATE suppliers_adresses SET deleted_at = NOW() WHERE supplier_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID supplierAddressId);
}
