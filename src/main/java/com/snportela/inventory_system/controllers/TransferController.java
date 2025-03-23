package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.domain.dto.TransferDto;
import com.snportela.inventory_system.domain.entities.Transfer;
import com.snportela.inventory_system.mappers.TransferMapper;
import com.snportela.inventory_system.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private TransferService transferService;

    private TransferMapper transferMapper;

    public TransferController(TransferService transferService, TransferMapper transferMapper) {
        this.transferService = transferService;
        this.transferMapper = transferMapper;
    }

    @GetMapping
    public ResponseEntity<List<TransferDto>> listTransfers() {
        List<TransferDto> transferList = transferService.findAll().stream().map(transferMapper::transferToDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(transferList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDto> getTransfer(@PathVariable("id") UUID transferId) {
        Transfer foundTransfer = transferService.findOne(transferId);
        return ResponseEntity.status(HttpStatus.FOUND).body(transferMapper.transferToDto(foundTransfer));
    }

    @PostMapping
    public ResponseEntity<TransferDto> createTransfer(@RequestBody TransferDto transferDto) {
        Transfer savedTransfer = transferService.save(transferMapper.dtoToTransfer(transferDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(transferMapper.transferToDto(savedTransfer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferDto> updateTransfer(
            @PathVariable("id") UUID transferId, @RequestBody TransferDto transferDto
    ) {
        Transfer updatedTransfer = transferService.update(transferId, transferMapper.dtoToTransfer(transferDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(transferMapper.transferToDto(updatedTransfer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable("id") UUID transferId) {
        transferService.delete(transferId);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted transfer with ID: " + transferId);
    }
}
