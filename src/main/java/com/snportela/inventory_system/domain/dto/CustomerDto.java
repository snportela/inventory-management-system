package com.snportela.inventory_system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private UUID customerId;

    private String name;

    private String phone;

    private String email;
}
