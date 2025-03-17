package com.snportela.inventory_system.mappers.impl;

import com.snportela.inventory_system.domain.dto.CustomerAdressDto;
import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;
import com.snportela.inventory_system.mappers.CustomerAdressMapper;
import com.snportela.inventory_system.mappers.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerAdressMapperImpl implements CustomerAdressMapper {

    private final CustomerMapper customerMapper;

    public CustomerAdressMapperImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerAdressEntity fromDto(CustomerAdressDto customerAdressDto) {
        return new CustomerAdressEntity(
                customerAdressDto.customerAdressId(),
                customerMapper.fromDto(customerAdressDto.customer()),
                customerAdressDto.street(),
                customerAdressDto.district(),
                customerAdressDto.number(),
                customerAdressDto.city(),
                customerAdressDto.state(),
                customerAdressDto.postalCode(),
                customerAdressDto.details(),
                customerAdressDto.receiverName()
        );
    }

    @Override
    public CustomerAdressDto toDto(CustomerAdressEntity customerAdressEntity) {
        return new CustomerAdressDto(
                customerAdressEntity.getCustomerAdressId(),
                customerMapper.toDto(customerAdressEntity.getCustomer()),
                customerAdressEntity.getStreet(),
                customerAdressEntity.getDistrict(),
                customerAdressEntity.getNumber(),
                customerAdressEntity.getCity(),
                customerAdressEntity.getState(),
                customerAdressEntity.getPostalCode(),
                customerAdressEntity.getDetails(),
                customerAdressEntity.getReceiverName()
        );
    }
}
