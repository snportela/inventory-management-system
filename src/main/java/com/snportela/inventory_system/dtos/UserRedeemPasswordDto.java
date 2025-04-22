package com.snportela.inventory_system.dtos;

import jakarta.validation.constraints.Email;

public record UserRedeemPasswordDto(@Email String email) {
}
