package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.SupplierAdressDto;
import com.snportela.inventory_system.domain.entities.SupplierAdress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierAdressMapper {

    SupplierAdressDto supplierAdressToDto(SupplierAdress supplierAdress);

    SupplierAdress dtoToSupplierAdress(SupplierAdressDto supplierAdressDto);
}
