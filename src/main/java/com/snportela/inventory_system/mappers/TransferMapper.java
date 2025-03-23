package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.TransferDto;
import com.snportela.inventory_system.domain.entities.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMapper {

    TransferDto transferToDto(Transfer transfer);

    Transfer dtoToTransfer(TransferDto transferDto);
}
