package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
}
