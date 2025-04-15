package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.SupplierAddress;
import com.snportela.inventory_system.dtos.SupplierAddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierAddressMapper {

    SupplierAddressDto supplierAddressToDto(SupplierAddress supplierAddress);

    SupplierAddress dtoToSupplierAddress(SupplierAddressDto supplierAddressDto);
}
