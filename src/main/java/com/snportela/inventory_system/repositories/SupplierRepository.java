package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.Supplier;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@NonNullApi
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    Page<Supplier> findAll(Pageable pageable);

}
