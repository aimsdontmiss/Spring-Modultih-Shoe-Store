package com.example.ShlopApp.Identity.Application.internals.interactor;

import com.example.ShlopApp.Identity.Application.internals.command.RegisterCommand;
import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Domain.port.AccountRepoPort;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountEntity;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountPersistenceMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterUseCase {
    private final AccountRepoPort repository;
    private final AccountPersistenceMapper mapper;
    private final PasswordEncoder encoder;


    public RegisterUseCase(
            AccountRepoPort repository,
            AccountPersistenceMapper mapper,
            PasswordEncoder encoder
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public Account execute(RegisterCommand command) {

        String hash = encoder.encode(command.password());

        Account account = new Account(
                command.email(),
                command.username(),
                hash
        );

        AccountEntity entity = mapper.toEntity(account);
        repository.save(entity);
        return account;
    }
}
