package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.Order;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.OrderRepository;
import com.snportela.inventory_system.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findOne(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Order update(UUID orderId, Order order) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(NotFoundException::new);

        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setCustomerAddress(order.getCustomerAddress());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setExpectedDate(order.getExpectedDate());
        existingOrder.setActualDate(order.getActualDate());
        existingOrder.setPaymentType(order.getPaymentType());
        existingOrder.setPaymentStatus(order.getPaymentStatus());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setTrackingNumber(order.getTrackingNumber());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void delete(UUID orderId) {
        orderRepository.findById(orderId).orElseThrow(NotFoundException::new);
        orderRepository.deleteById(orderId);
    }
}
