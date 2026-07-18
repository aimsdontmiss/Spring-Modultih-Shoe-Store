package com.example.ShlopApp.Identity.Application.internals.interactor;

import com.example.ShlopApp.Identity.Application.api.IdentityFacade;
import com.example.ShlopApp.Identity.Application.api.AccountInfo;
import com.example.ShlopApp.Identity.Application.api.CurrentAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentityFacadeImpl implements IdentityFacade {


    @Override
    public Optional<AccountInfo> currentAccount() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CurrentAccount account) {

            return Optional.of(
                    new AccountInfo(
                            account.accountId(),
                            account.username()
                    )
            );
        }

        return Optional.empty();
    }
}
