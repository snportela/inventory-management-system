package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.Customer;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

    @Query(value = "SELECT * FROM customers WHERE deleted_at IS NULL", nativeQuery = true)
    Page<Customer> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM customers WHERE customer_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<Customer> findById(UUID customerId);

    @Modifying
    @Query(value = "UPDATE customers SET deleted_at = NOW() WHERE customer_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID customerId);
}
