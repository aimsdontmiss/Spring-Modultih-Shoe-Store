package com.example.ShlopApp.Identity.Infrastructure.persistence;


import com.example.ShlopApp.Identity.Domain.ValueObjects.PasswordHash;
import com.example.ShlopApp.Identity.Domain.ValueObjects.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "STG_ACCOUNTS")
@Getter
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    private String email;
    private String username;
    private String passwordHash;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(
//            name = "STG_ACCOUNT_ROLES",
//            joinColumns = @JoinColumn(name = "account_id")
//    )
//    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;

    public AccountEntity(
            String email,
            String username,
            String passwordHash
//            Set<Role> roles
    ) {
        this.email = Objects.requireNonNull(email);
        this.username = Objects.requireNonNull(username);
        this.passwordHash = Objects.requireNonNull(passwordHash);
//        this.roles = Objects.requireNonNull(roles);
    }

    public void changePassword(PasswordHash newHash) {
        this.passwordHash = Objects.requireNonNull(newHash.value());
    }

}
