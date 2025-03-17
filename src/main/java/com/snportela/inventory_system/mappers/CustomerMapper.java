package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.CustomerDto;
import com.snportela.inventory_system.domain.entities.CustomerEntity;

public interface CustomerMapper {

    CustomerEntity fromDto(CustomerDto customerDto);

    CustomerDto toDto(CustomerEntity customerEntity);
}
