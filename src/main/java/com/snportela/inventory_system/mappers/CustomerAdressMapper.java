package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.CustomerAdressDto;
import com.snportela.inventory_system.domain.entities.CustomerAdress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerAdressMapper {

    CustomerAdressDto customerAdressToDto(CustomerAdress customerAdress);

    CustomerAdress dtoToCustomerAdress(CustomerAdressDto customerAdressDto);
}
