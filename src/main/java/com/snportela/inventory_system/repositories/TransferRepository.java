package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.Transfer;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface TransferRepository extends JpaRepository<Transfer, UUID> {

    @Query(value = "SELECT * FROM transfers WHERE deleted_at IS NULL", nativeQuery = true)
    Page<Transfer> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM transfers WHERE transfer_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<Transfer> findById(UUID transferId);

    @Modifying
    @Query(value = "UPDATE transfers SET deleted_at = NOW() WHERE transfer_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID transferId);
}
