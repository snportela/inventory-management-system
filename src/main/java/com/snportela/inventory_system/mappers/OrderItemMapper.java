package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.OrderItemDto;
import com.snportela.inventory_system.domain.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapper {

    OrderItemDto orderItemToDto(OrderItem orderItem);

    OrderItem dtoToOrderItem(OrderItemDto orderItemDto);
}
