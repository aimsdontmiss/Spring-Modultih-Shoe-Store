//package com.example.ShlopApp.Cart.Infrastructure.adapter;
//
//
//import com.example.ShlopApp.Cart.Application.internals.interactor.CartOwnerResolver;
//import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
//import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CustomerOwner;
//import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
//import com.example.ShlopApp.Identity.Infrastructure.security.AccountPrincipal;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.security.core.Authentication;
//
//
//@Component
//public class SpringSecurityCartOwnerResolver
//        implements CartOwnerResolver {
//
//    private final HttpServletRequest request;
//
//    public SpringSecurityCartOwnerResolver(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    @Override
//    public CartOwner resolve() {
//
//        Authentication auth =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        if(auth != null && auth.isAuthenticated()
//                && !(auth instanceof AnonymousAuthenticationToken)) {
//
//            AccountPrincipal principal =
//                    (AccountPrincipal) auth.getPrincipal();
//
//            return CustomerOwner.of(String.valueOf(principal.getAccount().getAccountId()));
//        }
//
//        String session = request.getSession().getId();
//
//        return SessionOwner.of(session);
//    }
//
//}