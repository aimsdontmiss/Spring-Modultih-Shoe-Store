package com.example.ShlopApp.Identity.Infrastructure.adapter;

import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Domain.port.AccountRepoPort;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountEntity;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountRepoAdapter implements AccountRepoPort {
    private final JpaAccountRepository repository;
    private final AccountPersistenceMapper mapper = new AccountPersistenceMapper();

    public AccountRepoAdapter(JpaAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> findByUser(String username) {
        Optional<AccountEntity> entity = repository.findByUsername(username);

        if (entity.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(mapper.toDomain(entity.get()));
        }
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return Optional.of(
                mapper.toDomain(repository.findById(id).orElse(null))
        );
    }

    @Override
    public Account save(AccountEntity entity) {
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void delete(Account account) {
        repository.delete(mapper.toEntity(account));
    }
}



