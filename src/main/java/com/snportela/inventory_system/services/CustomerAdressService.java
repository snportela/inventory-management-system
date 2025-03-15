package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerAdressService {

    CustomerAdressEntity save(CustomerAdressEntity customerAdressEntity);

    List<CustomerAdressEntity> findAll();

    Optional<CustomerAdressEntity> findOne(UUID customerAdressId);

    boolean isExists(UUID customerAdressId);

    void delete(UUID customerAdressId);
}
