package com.snportela.inventory_system.dtos;

import com.snportela.inventory_system.domain.UserRole;

import java.util.UUID;

public record UserDto(

        UUID userId,

        String email,

        String name,

        String phone,

        UserRole role
) {
}
