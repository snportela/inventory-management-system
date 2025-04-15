package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.OrderItem;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.OrderItemRepository;
import com.snportela.inventory_system.services.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Page<OrderItem> findAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    @Override
    public OrderItem findOneByProduct(UUID productId) {
        return orderItemRepository.findByOrderItemByOrderItemId_ProductId(productId).orElseThrow(NotFoundException::new);
    }

    @Override
    public OrderItem findOneByOrder(UUID orderId) {
        return orderItemRepository.findByOrderItemByOrderItemId_OrderId(orderId).orElseThrow(NotFoundException::new);
    }

    @Override
    public OrderItem updateByProduct(UUID productId, OrderItem orderItem) {
        OrderItem existingOrderItem = orderItemRepository.findByOrderItemByOrderItemId_ProductId(productId).orElseThrow(NotFoundException::new);
        existingOrderItem.setOrderQuantity(orderItem.getOrderQuantity());

        return orderItemRepository.save(existingOrderItem);
    }

    @Override
    public OrderItem updateByOrder(UUID orderId, OrderItem orderItem) {
        OrderItem existingOrderItem = orderItemRepository.findByOrderItemByOrderItemId_OrderId(orderId).orElseThrow(NotFoundException::new);
        existingOrderItem.setOrderQuantity(orderItem.getOrderQuantity());

        return orderItemRepository.save(existingOrderItem);
    }

    @Transactional
    @Override
    public void deleteByProduct(UUID productId) {
        orderItemRepository.findByOrderItemByOrderItemId_ProductId(productId).orElseThrow(NotFoundException::new);
        orderItemRepository.deleteByProductId(productId);
    }

    @Transactional
    @Override
    public void deleteByOrder(UUID orderId) {
        orderItemRepository.findByOrderItemByOrderItemId_OrderId(orderId).orElseThrow(NotFoundException::new);
        orderItemRepository.deleteByOrderId(orderId);
    }
}
