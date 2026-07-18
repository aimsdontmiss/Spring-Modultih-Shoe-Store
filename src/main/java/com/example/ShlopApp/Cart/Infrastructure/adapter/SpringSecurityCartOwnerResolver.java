package com.example.ShlopApp.Cart.Infrastructure.adapter;


import com.example.ShlopApp.Cart.Application.internals.interactor.CartOwnerResolver;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CustomerOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import com.example.ShlopApp.Identity.Application.api.IdentityFacade;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SpringSecurityCartOwnerResolver
        implements CartOwnerResolver {

    private final HttpServletRequest request;
    private final IdentityFacade facade;

    public SpringSecurityCartOwnerResolver(
            HttpServletRequest request,
            IdentityFacade facade
    ) {
            this.request = request;
            this.facade = facade;
    }
//
//    @Override
//    public CartOwner resolve() {

//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        if (auth != null
//                && auth.isAuthenticated()
//                && !(auth instanceof AnonymousAuthenticationToken)) {
//
//            AccountPrincipal principal =
//                    (AccountPrincipal) auth.getPrincipal();
//            System.out.println("PRINCIPAL ACCOUNT_ID: " + principal.getAccountId());
//
//            return CustomerOwner.of(principal.getAccountId());
//        }
//
//        System.out.println(
//                "AUTHENTICATED: " +
//                        SecurityContextHolder.getContext().getAuthentication()
//        );
//
//        System.out.println(
//                "SESSION: " +
//                        request.getSession().getId()
//        );
//
//        return SessionOwner.of(
//                UUID.fromString(request.getSession().getId())
//        );

//        Optional<AccountInfo> account = facade.currentAccount();
//
//        if (account != null) {
//            return CustomerOwner.of(account.accountId());
//        }
//
//        return SessionOwner.of(
//                UUID.fromString(request.getSession().getId())
//        );
//
//
//
//    }
//
//}

    @Override
    public CartOwner resolve() {

        return facade.currentAccount()
                .<CartOwner>map(account ->
                        CustomerOwner.of(account.accountId())
                )
                .orElseGet(() ->
                        SessionOwner.of(
                                UUID.fromString(
                                        request.getSession().getId()
                                )
                        )
                );
    }
}