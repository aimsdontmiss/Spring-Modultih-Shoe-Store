package com.example.ShlopApp.Identity.Application.api;


import java.util.Optional;

public interface IdentityFacade {
    Optional<AccountInfo> currentAccount();
}
