package com.snportela.inventory_system.dtos;

import com.snportela.inventory_system.domain.UserRole;

public record RegisterDto(
        String email,

        String password,

        String name,

        String phone,

        UserRole role
) {
}
