package com.example.ShlopApp.Identity.Domain.ValueObjects;

import java.util.UUID;

public record AccountId(
        UUID accountId
) {
    public static AccountId of(UUID accountId) {
        return new AccountId(accountId);
    }
}
