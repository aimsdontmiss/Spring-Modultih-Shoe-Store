package com.example.ShlopApp.Cart.Application.api;


import java.util.UUID;

public sealed interface CartAccessContext
        permits CustomerAccess, GuestAccess {
}
