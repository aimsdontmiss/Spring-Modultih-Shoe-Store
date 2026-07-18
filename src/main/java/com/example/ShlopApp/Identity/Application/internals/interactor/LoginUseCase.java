package com.example.ShlopApp.Identity.Application.internals.interactor;

import com.example.ShlopApp.Identity.Application.internals.command.LoginCommand;
import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Domain.port.AccountRepoPort;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountEntity;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountPersistenceMapper;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginUseCase {
    private final AccountRepoPort repository;
    private final AccountPersistenceMapper mapper;
    private final PasswordEncoder encoder;


    public LoginUseCase(
            AccountRepoPort repository,
            AccountPersistenceMapper mapper,
            PasswordEncoder encoder
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public Account execute(LoginCommand command) {

        System.out.println("LOGIN USERNAME: " + command.username());

        Optional<Account> result = repository.findByUser(command.username());

        System.out.println("FOUND ACCOUNT: " + result.isPresent());

        if (result.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        System.out.println("HASH IN DB: " + result.get().getPasswordHash());

        boolean matches = encoder.matches(
                command.password(),
                result.get().getPasswordHash()
        );

        System.out.println("PASSWORD MATCH: " + matches);

        if (!matches) {
            throw new RuntimeException("Invalid credentials");
        }

        return result.orElseThrow();
    }

}
