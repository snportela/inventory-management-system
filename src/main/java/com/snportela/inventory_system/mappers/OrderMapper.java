package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.dtos.OrderDto;
import com.snportela.inventory_system.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderDto orderToDto(Order order);

    Order dtoToOrder(OrderDto orderDto);

}
