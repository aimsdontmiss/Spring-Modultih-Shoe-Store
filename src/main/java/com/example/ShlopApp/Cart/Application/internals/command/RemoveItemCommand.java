package com.example.ShlopApp.Cart.Application.internals.command;

import com.example.ShlopApp.Cart.Domain.model.CartItem;

public record RemoveItemCommand(
        CartItem cartItem
) { }
