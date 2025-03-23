package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order save(Order order);

    List<Order> findAll();

    Order findOne(UUID orderId);

    Order update(UUID orderId, Order order);

    void delete(UUID orderId);
}
