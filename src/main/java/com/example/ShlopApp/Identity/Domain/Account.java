package com.example.ShlopApp.Identity.Domain;

import com.example.ShlopApp.Identity.Application.internals.command.RegisterCommand;
import com.example.ShlopApp.Identity.Domain.ValueObjects.Role;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class Account {
    private UUID accountId;
    private String email;
    private String username;
    private String passwordHash;

    public Account(
            String email,
            String username,
            String passwordHash
    ) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Account(
            UUID accountId,
            String email,
            String username,
            String passwordHash
    ) {
        this.accountId = accountId;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public boolean login(String username, String password) {
        return this.passwordHash.equals(password) && this.username.equals(username);
    }
}
