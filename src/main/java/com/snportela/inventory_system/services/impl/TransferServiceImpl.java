package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.Transfer;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.TransferRepository;
import com.snportela.inventory_system.services.TransferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer findOne(UUID transferId) {
        return transferRepository.findById(transferId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Transfer update(UUID transferId, Transfer transfer) {
        Transfer existingTransfer = transferRepository.findById(transferId).orElseThrow(NotFoundException::new);

        existingTransfer.setProduct(transfer.getProduct());
        existingTransfer.setQuantity(transfer.getQuantity());
        existingTransfer.setTransferDate(transfer.getTransferDate());
        existingTransfer.setTransferType(transfer.getTransferType());
        existingTransfer.setDescription(transfer.getDescription());

        return transferRepository.save(existingTransfer);
    }

    @Override
    public void delete(UUID transferId) {
        transferRepository.findById(transferId).orElseThrow(NotFoundException::new);
        transferRepository.deleteById(transferId);
    }
}
