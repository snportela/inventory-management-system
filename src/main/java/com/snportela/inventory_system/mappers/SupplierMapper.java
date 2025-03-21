package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.SupplierDto;
import com.snportela.inventory_system.domain.entities.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper {

    SupplierDto supplierToDto(Supplier supplier);

    Supplier dtoToSupplier(SupplierDto supplierDto);
}
