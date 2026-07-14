package com.example.ShlopApp.Cart.Domain.model;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CustomerOwner;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class CartFactory {

    // For anon users
    public Cart create(SessionOwner sessionId) {
        return new Cart(
                CartId.create(),
                sessionId,
                new ArrayList<>(),
                Instant.now(),
                Instant.now()
        );
    }

    // For logged in users
    public Cart create(CustomerOwner customerId) {
        return new Cart(
                CartId.create(),
                customerId,
                new ArrayList<>(),
                Instant.now(),
                Instant.now()
        );
    }

}
