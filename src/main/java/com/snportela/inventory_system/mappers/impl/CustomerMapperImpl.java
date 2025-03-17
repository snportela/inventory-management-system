package com.snportela.inventory_system.mappers.impl;

import com.snportela.inventory_system.domain.dto.CustomerDto;
import com.snportela.inventory_system.domain.entities.CustomerEntity;
import com.snportela.inventory_system.mappers.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerEntity fromDto(CustomerDto customerDto) {
        return new CustomerEntity(
                customerDto.customerId(),
                customerDto.name(),
                customerDto.phone(),
                customerDto.email()
        );
    }

    @Override
    public CustomerDto toDto(CustomerEntity customerEntity) {
        return new CustomerDto(
                customerEntity.getCustomerId(),
                customerEntity.getName(),
                customerEntity.getPhone(),
                customerEntity.getEmail()
        );
    }
}
