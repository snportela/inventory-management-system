package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.SupplierAdress;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface SupplierAdressRepository extends JpaRepository<SupplierAdress, UUID> {

    @Query(value = "SELECT * FROM suppliers_adresses WHERE deleted_at IS NULL", nativeQuery = true)
    List<SupplierAdress> findAll();

    @Query(value = "SELECT * FROM suppliers_adresses WHERE supplier_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<SupplierAdress> findById(UUID supplierAdressId);

    @Modifying
    @Query(value = "UPDATE suppliers_adresses SET deleted_at = NOW() WHERE supplier_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID supplierAdressId);
}
