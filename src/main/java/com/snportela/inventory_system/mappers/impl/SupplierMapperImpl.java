package com.snportela.inventory_system.mappers.impl;

import com.snportela.inventory_system.domain.dto.SupplierDto;
import com.snportela.inventory_system.domain.entities.SupplierEntity;
import com.snportela.inventory_system.mappers.SupplierMapper;

public class SupplierMapperImpl implements SupplierMapper {
    @Override
    public SupplierEntity fromDto(SupplierDto supplierDto) {
        return new SupplierEntity(
                supplierDto.supplierId(),
                supplierDto.name(),
                supplierDto.phone(),
                supplierDto.email(),
                supplierDto.cnpj()
        );
    }

    @Override
    public SupplierDto toDto(SupplierEntity supplierEntity) {
        return new SupplierDto(
                supplierEntity.getSupplierId(),
                supplierEntity.getName(),
                supplierEntity.getPhone(),
                supplierEntity.getEmail(),
                supplierEntity.getCnpj()
        );
    }
}
