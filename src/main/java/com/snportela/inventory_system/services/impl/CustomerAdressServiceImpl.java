package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.CustomerAdress;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.CustomerAdressRepository;
import com.snportela.inventory_system.services.CustomerAdressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerAdressServiceImpl implements CustomerAdressService {

    private final CustomerAdressRepository customerAdressRepository;

    public CustomerAdressServiceImpl(CustomerAdressRepository customerAdressRepository) {
        this.customerAdressRepository = customerAdressRepository;
    }

    @Override
    public CustomerAdress save(CustomerAdress customerAdress) {
        return customerAdressRepository.save(customerAdress);
    }

    @Override
    public List<CustomerAdress> findAll() {
        return customerAdressRepository.findAll();
    }

    @Override
    public CustomerAdress findOne(UUID customerAdressId) {
        return customerAdressRepository.findById(customerAdressId).orElseThrow(NotFoundException::new);
    }

    @Override
    public CustomerAdress update(UUID customerAdressId, CustomerAdress customerAdress) {
        CustomerAdress existingAdress = customerAdressRepository.findById(customerAdressId).orElseThrow(NotFoundException::new);

        existingAdress.setCustomer(customerAdress.getCustomer());
        existingAdress.setStreet(customerAdress.getStreet());
        existingAdress.setDistrict(customerAdress.getDistrict());
        existingAdress.setNumber(customerAdress.getNumber());
        existingAdress.setCity(customerAdress.getCity());
        existingAdress.setState(customerAdress.getState());
        existingAdress.setPostalCode(customerAdress.getPostalCode());
        existingAdress.setDetails(customerAdress.getDetails());
        existingAdress.setReceiverName(customerAdress.getReceiverName());

        return customerAdressRepository.save(existingAdress);
    }

    @Override
    public void delete(UUID customerAdressId) {
        customerAdressRepository.findById(customerAdressId).orElseThrow(NotFoundException::new);
        customerAdressRepository.deleteById(customerAdressId);
    }
}
