package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.entities.CustomerAdress;

import java.util.List;
import java.util.UUID;

public interface CustomerAdressService {

    CustomerAdress save(CustomerAdress customerAdress);

    List<CustomerAdress> findAll();

    CustomerAdress findOne(UUID customerAdressId);

    CustomerAdress update(UUID customerAdressId, CustomerAdress customerAdress);

    void delete(UUID customerAdressId);
}
