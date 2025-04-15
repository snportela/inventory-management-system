package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.Order;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "SELECT * FROM orders WHERE deleted_at IS NULL", nativeQuery = true)
    Page<Order> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<Order> findById(UUID orderId);

    @Modifying
    @Query(value = "UPDATE orders SET deleted_at = NOW() WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID orderId);
}
