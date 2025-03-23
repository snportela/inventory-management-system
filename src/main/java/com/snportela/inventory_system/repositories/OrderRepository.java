package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "SELECT * FROM orders WHERE deleted_at IS NULL", nativeQuery = true)
    List<Order> findAll();

    @Query(value = "SELECT * FROM orders WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<Order> findById(UUID orderId);

    @Modifying
    @Query(value = "UPDATE orders SET deleted_at = NOW() WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID orderId);
}
