package com.example.ShlopApp.Identity.Domain;

import com.example.ShlopApp.Identity.Domain.ValueObjects.AccountId;
import com.example.ShlopApp.Identity.Domain.ValueObjects.Role;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class Account {
    private final UUID accountId;
    private String email;
    private String username;
    private String passwordHash;
    private Set<Role> roles;

    public Account(
            UUID accountId,
            String email,
            String username,
            String passwordHash,
            Set<Role> roles
    ) {
        this.accountId = accountId;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }


}
