package com.snportela.inventory_system.services;

import com.snportela.inventory_system.domain.CustomerAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerAddressService {

    CustomerAddress save(CustomerAddress customerAddress);

    Page<CustomerAddress> findAll(Pageable pageable);

    CustomerAddress findOne(UUID customerAddressId);

    CustomerAddress update(UUID customerAddressId, CustomerAddress customerAddress);

    void delete(UUID customerAddressId);
}
