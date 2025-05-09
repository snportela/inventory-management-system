package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.OrderItemDto;
import com.snportela.inventory_system.domain.OrderItem;
import com.snportela.inventory_system.mappers.OrderItemMapper;
import com.snportela.inventory_system.services.OrderItemService;
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
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    private final OrderItemMapper orderItemMapper;

    public OrderItemController(OrderItemService orderItemService, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderItemMapper = orderItemMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> listOrderItems(@RequestParam int page, @RequestParam int size,
                                                             @RequestParam String sortField, @RequestParam String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OrderItem> orderItemList = orderItemService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemList.stream().map(orderItemMapper::orderItemToDto).collect(Collectors.toList()));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemByProductId(@PathVariable("id") UUID productId) {
        OrderItem foundOrderItem = orderItemService.findOneByProduct(productId);
        return ResponseEntity.status(HttpStatus.FOUND).body(orderItemMapper.orderItemToDto(foundOrderItem));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemByOrderId(@PathVariable("id") UUID orderId) {
        OrderItem foundOrderItem = orderItemService.findOneByOrder(orderId);
        return ResponseEntity.status(HttpStatus.FOUND).body(orderItemMapper.orderItemToDto(foundOrderItem));
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> crateOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItem savedOrderItem = orderItemService.save(orderItemMapper.dtoToOrderItem(orderItemDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemMapper.orderItemToDto(savedOrderItem));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItemByProduct(
            @PathVariable("id") UUID productId, @RequestBody OrderItemDto orderItemDto
            ) {
        OrderItem updatedOrderItem = orderItemService.updateByProduct(productId, orderItemMapper.dtoToOrderItem(orderItemDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemMapper.orderItemToDto(updatedOrderItem));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItemByOrder(
            @PathVariable("id") UUID orderId, @RequestBody OrderItemDto orderItemDto
    ) {
        OrderItem updatedOrderItem = orderItemService.updateByOrder(orderId, orderItemMapper.dtoToOrderItem(orderItemDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemMapper.orderItemToDto(updatedOrderItem));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteOrderItemByProduct(@PathVariable("id") UUID productId) {
        orderItemService.deleteByProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted order item with productID: " + productId);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrderItemByOrder(@PathVariable("id") UUID orderId) {
        orderItemService.deleteByOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted order item with orderID: " + orderId);
    }

}
