package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.CustomerAddress;
import com.snportela.inventory_system.dtos.CustomerAddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerAddressMapper {

    CustomerAddressDto customerAddressToDto(CustomerAddress customerAddress);

    CustomerAddress dtoToCustomerAddress(CustomerAddressDto customerAddressDto);
}
