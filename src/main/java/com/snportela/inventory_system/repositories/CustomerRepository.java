package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.Customer;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@NonNullApi
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

    Page<Customer> findAll(Pageable pageable);

}
