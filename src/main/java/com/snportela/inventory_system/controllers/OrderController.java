package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.OrderDto;
import com.snportela.inventory_system.domain.Order;
import com.snportela.inventory_system.mappers.OrderMapper;
import com.snportela.inventory_system.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> listOrders(@RequestParam int page, @RequestParam int size,
                                                     @RequestParam String sortField, @RequestParam String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Order> orderList = orderService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(orderList.stream().map(orderMapper::orderToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") UUID orderId) {
        Order foundOrder = orderService.findOne(orderId);
        return ResponseEntity.status(HttpStatus.FOUND).body(orderMapper.orderToDto(foundOrder));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order savedOrder = orderService.save(orderMapper.dtoToOrder(orderDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToDto(savedOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(
            @PathVariable("id") UUID orderId, @RequestBody OrderDto orderDto
    ) {
        Order updatedOrder = orderService.update(orderId, orderMapper.dtoToOrder(orderDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") UUID orderId) {
        orderService.delete(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted order with ID:" + orderId);
    }
}

