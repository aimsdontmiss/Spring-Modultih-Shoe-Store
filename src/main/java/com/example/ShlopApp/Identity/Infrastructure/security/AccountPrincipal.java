package com.example.ShlopApp.Identity.Infrastructure.security;

import com.example.ShlopApp.Identity.Application.api.CurrentAccount;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AccountPrincipal
        implements UserDetails, Serializable, CurrentAccount {
//    private final Account account;


    private final UUID accountId;
    private final String username;
    private final String passwordHash;

    public AccountPrincipal(
            UUID accountId,
            String username,
            String passwordHash
    ) {
        this.accountId = accountId;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    public UUID getAccountId() {
        return this.accountId;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public @Nullable String getPassword() {
        return this.passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public UUID accountId() {
        return this.accountId;
    }

    @Override
    public String username() {
        return this.username;
    }
}
