package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransferService {

    Transfer save(Transfer transfer);

    Page<Transfer> findAll(Pageable pageable);

    Transfer findOne(UUID transferId);

    Transfer update(UUID transferId, Transfer transfer);

    void delete(UUID transferId);
}
