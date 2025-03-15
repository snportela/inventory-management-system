package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerAdressRepository extends JpaRepository<CustomerAdressEntity, UUID> {
}
