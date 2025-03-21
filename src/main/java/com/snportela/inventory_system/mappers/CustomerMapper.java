package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.CustomerDto;
import com.snportela.inventory_system.domain.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(CustomerDto customerDto);
}
