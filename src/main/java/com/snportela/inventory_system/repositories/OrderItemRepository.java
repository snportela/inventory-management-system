package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.OrderItem;
import com.snportela.inventory_system.domain.OrderItemId;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    Page<OrderItem> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM order_items WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<OrderItem> findByOrderItemByOrderItemId_OrderId(UUID orderId);

    @Query(value = "SELECT * FROM order_items WHERE product_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<OrderItem> findByOrderItemByOrderItemId_ProductId(UUID productId);

    @Modifying
    @Query(value = "UPDATE order_items SET deleted_at = NOW() WHERE order_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteByOrderId(UUID orderId);

    @Modifying
    @Query(value = "UPDATE order_items SET deleted_at = NOW() WHERE product_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteByProductId(UUID productId);
}
