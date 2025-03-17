package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.SupplierDto;
import com.snportela.inventory_system.domain.entities.SupplierEntity;

public interface SupplierMapper {

    SupplierEntity fromDto(SupplierDto supplierDto);

    SupplierDto toDto(SupplierEntity supplierEntity);
}
