package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.CustomerAdressEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerAdressService {

    CustomerAdressEntity save(CustomerAdressEntity customerAdressEntity);

    List<CustomerAdressEntity> findAll();

    CustomerAdressEntity findOne(UUID customerAdressId);

    CustomerAdressEntity update(UUID customerAdressId, CustomerAdressEntity customerAdressEntity);

    void delete(UUID customerAdressId);
}
