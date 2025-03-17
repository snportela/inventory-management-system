package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.CustomerAdressDto;
import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;

public interface CustomerAdressMapper {

    CustomerAdressEntity fromDto(CustomerAdressDto customerAdressDto);

    CustomerAdressDto toDto(CustomerAdressEntity customerAdressEntity);
}
