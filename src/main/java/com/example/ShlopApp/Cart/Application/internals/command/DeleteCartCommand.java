package com.example.ShlopApp.Cart.Application.internals.command;

import java.util.UUID;

public record DeleteCartCommand(
        UUID cartId
) {
}
