package com.snportela.inventory_system.repositories;

import com.snportela.inventory_system.domain.entities.CustomerAdress;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NonNullApi
public interface CustomerAdressRepository extends JpaRepository<CustomerAdress, UUID> {

    @Query(value = "SELECT * FROM customers_adresses WHERE deleted_at IS NULL", nativeQuery = true)
    List<CustomerAdress> findAll();

    @Query(value = "SELECT * FROM customers_adresses WHERE customer_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    Optional<CustomerAdress> findById(UUID customerAdressId);

    @Modifying
    @Query(value = "UPDATE customers_adresses SET deleted_at = NOW() WHERE customer_adress_id = ?1 AND deleted_at IS NULL", nativeQuery = true)
    void deleteById(UUID customerAdressId);
}
