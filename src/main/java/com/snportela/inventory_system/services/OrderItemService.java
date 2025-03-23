package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {

    OrderItem save(OrderItem orderItem);

    List<OrderItem> findAll();

    OrderItem findOneByProduct(UUID productId);

    OrderItem findOneByOrder(UUID orderId);

    OrderItem updateByProduct(UUID productId, OrderItem orderItem);

    OrderItem updateByOrder(UUID orderId, OrderItem orderItem);

    void deleteByProduct(UUID productId);

    void deleteByOrder(UUID orderId);
}
