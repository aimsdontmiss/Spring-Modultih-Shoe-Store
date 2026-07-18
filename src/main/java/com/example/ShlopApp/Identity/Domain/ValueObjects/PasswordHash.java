package com.example.ShlopApp.Identity.Domain.ValueObjects;

import java.util.Objects;

public record PasswordHash(String value) {
    public PasswordHash {
        Objects.requireNonNull(value);

        if (value.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be blank.");
        }
    }
}
