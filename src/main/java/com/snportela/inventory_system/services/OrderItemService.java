package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderItemService {

    OrderItem save(OrderItem orderItem);

    Page<OrderItem> findAll(Pageable pageable);

    OrderItem findOneByProduct(UUID productId);

    OrderItem findOneByOrder(UUID orderId);

    OrderItem updateByProduct(UUID productId, OrderItem orderItem);

    OrderItem updateByOrder(UUID orderId, OrderItem orderItem);

    void deleteByProduct(UUID productId);

    void deleteByOrder(UUID orderId);
}
