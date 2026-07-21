package com.example.ShlopApp.Cart.Domain.model;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CustomerOwner;
import com.example.ShlopApp.Cart.Infrastructure.adapter.SpringSecurityCartOwnerResolver;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class CartFactory {

    private final SpringSecurityCartOwnerResolver resolver;

    public CartFactory(SpringSecurityCartOwnerResolver resolver) {
        this.resolver = resolver;
    }

    // For anon users
    public Cart create(CartOwner owner) {
        return new Cart(
                CartId.create(),
                owner,
                new ArrayList<>(),
                Instant.now(),
                Instant.now()
        );
    }

//    // For logged in users
//    public Cart create(CustomerOwner ownerId) {
//        return new Cart(
//                CartId.create(),
//                ownerId,
//                new ArrayList<>(),
//                Instant.now(),
//                Instant.now()
//        );
//    }

}
