package com.example.ShlopApp.Identity.Domain.port;

import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Domain.ValueObjects.AccountId;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountEntity;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepoPort {
    Optional<Account> findByUser(String username);
    Optional<Account> findById(UUID id);
    Account save(AccountEntity entity);
    void delete(Account account);
}


