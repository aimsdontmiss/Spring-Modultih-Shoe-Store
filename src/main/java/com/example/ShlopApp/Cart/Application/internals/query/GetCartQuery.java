package com.example.ShlopApp.Cart.Application.internals.query;

import java.util.UUID;

public record GetCartQuery(
        UUID cartId
) { }
