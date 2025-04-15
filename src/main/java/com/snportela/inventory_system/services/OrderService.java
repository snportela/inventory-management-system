package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    Order save(Order order);

    Page<Order> findAll(Pageable pageable);

    Order findOne(UUID orderId);

    Order update(UUID orderId, Order order);

    void delete(UUID orderId);
}
