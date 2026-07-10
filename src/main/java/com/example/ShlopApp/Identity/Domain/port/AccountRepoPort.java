package com.example.ShlopApp.Identity.Domain.port;

import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Domain.ValueObjects.AccountId;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepoPort {
    Optional<Account> findByUsername(String username);

    Optional<Account> findById(UUID id);

    void save(Account account);
}
