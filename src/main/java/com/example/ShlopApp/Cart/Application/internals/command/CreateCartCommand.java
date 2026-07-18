package com.example.ShlopApp.Cart.Application.internals.command;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;

public record CreateCartCommand(
        CartOwner owner
) {}