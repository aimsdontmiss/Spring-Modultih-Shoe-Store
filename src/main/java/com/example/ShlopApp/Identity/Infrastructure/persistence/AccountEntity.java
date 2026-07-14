//package com.example.ShlopApp.Identity.Infrastructure.persistence;
//
//
//import com.example.ShlopApp.Identity.Domain.ValueObjects.Role;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.Set;
//import java.util.UUID;
//
//@Entity
//@Table(name = "STG_ACCOUNTS")
//@Getter
//@NoArgsConstructor
//public class AccountEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID accountId;
//    private String email;
//    private String username;
//    private String passwordHash;
//
//    private Set<Role> roles;
//
//    public AccountEntity(
//            UUID accountId,
//            String email,
//            String username,
//            String passwordHash,
//            Set<Role> roles
//    ) {
//        this.accountId = accountId;
//        this.email = email;
//        this.username = username;
//    }
//
//}
