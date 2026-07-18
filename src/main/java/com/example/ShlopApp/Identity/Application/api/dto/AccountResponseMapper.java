package com.example.ShlopApp.Identity.Application.api.dto;

import com.example.ShlopApp.Identity.Domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountResponseMapper {
    public AccountDto toDto(Account account) {

        return new AccountDto(
                account.getEmail(),
                account.getUsername(),
                account.getPasswordHash()
        );
    }
}
