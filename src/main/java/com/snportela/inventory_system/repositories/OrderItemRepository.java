package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.OrderItem;
import com.snportela.inventory_system.domain.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    @Query(value = "SELECT * FROM orders WHERE deleted_at IS NULL", nativeQuery = true)
    List<OrderItem> findAll();

    @Query(value = "SELECT * FROM orders WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<OrderItem> findByOrderId(OrderItemId orderItemId);

    @Modifying
    @Query(value = "UPDATE orders SET deleted_at = NOW() WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID orderId);
}
