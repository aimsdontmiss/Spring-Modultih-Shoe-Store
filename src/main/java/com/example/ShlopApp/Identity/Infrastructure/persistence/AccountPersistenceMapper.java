package com.example.ShlopApp.Identity.Infrastructure.persistence;

import com.example.ShlopApp.Identity.Domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountPersistenceMapper {
    public AccountEntity toEntity(Account account) {

        System.out.println("email = " + account.getEmail());
        System.out.println("username = " + account.getUsername());
        System.out.println("password = " + account.getPasswordHash());

        return new AccountEntity(
                account.getEmail(),
                account.getUsername(),
                account.getPasswordHash()
//                account.getRoles()
        );
    }

    public Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getAccountId(),
                entity.getEmail(),
                entity.getUsername(),
                entity.getPasswordHash()
        );
    }
}
