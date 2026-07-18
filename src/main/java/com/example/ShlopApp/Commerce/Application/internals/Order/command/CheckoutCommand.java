package com.example.ShlopApp.Commerce.Application.internals.Order.command;

import java.util.UUID;

public record CheckoutCommand(
        UUID cartId,
        UUID ownerId
) { }
