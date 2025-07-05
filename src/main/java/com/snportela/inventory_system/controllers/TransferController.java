package com.snportela.inventory_system.controllers;

import com.snportela.inventory_system.dtos.TransferDto;
import com.snportela.inventory_system.domain.Transfer;
import com.snportela.inventory_system.mappers.TransferMapper;
import com.snportela.inventory_system.services.TransferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    private final TransferMapper transferMapper;

    public TransferController(TransferService transferService, TransferMapper transferMapper) {
        this.transferService = transferService;
        this.transferMapper = transferMapper;
    }

    @GetMapping
    public ResponseEntity<List<TransferDto>> listTransfers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestParam(required = false, defaultValue = "productId") String sortField,
            @RequestParam(required = false, defaultValue = "asc") String order){
        Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Transfer> transferList = transferService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(transferList.stream().map(transferMapper::transferToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDto> getTransfer(@PathVariable("id") UUID transferId) {
        Transfer foundTransfer = transferService.findOne(transferId);
        return ResponseEntity.status(HttpStatus.OK).body(transferMapper.transferToDto(foundTransfer));
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
