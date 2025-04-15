package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.CustomerAddress;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {

    @Query(value = "SELECT * FROM customers_adresses WHERE deleted_at IS NULL", nativeQuery = true)
    Page<CustomerAddress> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM customers_adresses WHERE customer_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<CustomerAddress> findById(UUID customerAddressId);

    @Modifying
    @Query(value = "UPDATE customers_adresses SET deleted_at = NOW() WHERE customer_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID customerAddressId);
}
