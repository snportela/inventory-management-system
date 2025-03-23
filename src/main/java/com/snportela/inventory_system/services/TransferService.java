package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.Transfer;

import java.util.List;
import java.util.UUID;

public interface TransferService {

    Transfer save(Transfer transfer);

    List<Transfer> findAll();

    Transfer findOne(UUID transferId);

    Transfer update(UUID transferId, Transfer transfer);

    void delete(UUID transferId);
}
