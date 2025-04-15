package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.CustomerAddress;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.CustomerAddressRepository;
import com.snportela.inventory_system.services.CustomerAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressServiceImpl(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    @Override
    public CustomerAddress save(CustomerAddress customerAddress) {
        return customerAddressRepository.save(customerAddress);
    }

    @Override
    public Page<CustomerAddress> findAll(Pageable pageable) {
        return customerAddressRepository.findAll(pageable);
    }

    @Override
    public CustomerAddress findOne(UUID customerAddressId) {
        return customerAddressRepository.findById(customerAddressId).orElseThrow(NotFoundException::new);
    }

    @Override
    public CustomerAddress update(UUID customerAddressId, CustomerAddress customerAddress) {
        CustomerAddress existingAddress = customerAddressRepository.findById(customerAddressId).orElseThrow(NotFoundException::new);

        existingAddress.setCustomer(customerAddress.getCustomer());
        existingAddress.setStreet(customerAddress.getStreet());
        existingAddress.setDistrict(customerAddress.getDistrict());
        existingAddress.setNumber(customerAddress.getNumber());
        existingAddress.setCity(customerAddress.getCity());
        existingAddress.setState(customerAddress.getState());
        existingAddress.setPostalCode(customerAddress.getPostalCode());
        existingAddress.setDetails(customerAddress.getDetails());
        existingAddress.setReceiverName(customerAddress.getReceiverName());

        return customerAddressRepository.save(existingAddress);
    }

    @Override
    public void delete(UUID customerAddressId) {
        customerAddressRepository.findById(customerAddressId).orElseThrow(NotFoundException::new);
        customerAddressRepository.deleteById(customerAddressId);
    }
}
