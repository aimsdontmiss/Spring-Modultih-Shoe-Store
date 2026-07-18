package com.example.ShlopApp.Identity.Application.api.dto;

public record AccountDto(
        String email,
        String username,
        String passwordHash
) {
}
