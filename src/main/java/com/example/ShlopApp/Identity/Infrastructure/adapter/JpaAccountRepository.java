package com.example.ShlopApp.Identity.Infrastructure.adapter;

import com.example.ShlopApp.Identity.Domain.Account;
import com.example.ShlopApp.Identity.Infrastructure.persistence.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByUsername(String username);
}


