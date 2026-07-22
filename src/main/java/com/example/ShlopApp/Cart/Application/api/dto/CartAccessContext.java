package com.example.ShlopApp.Cart.Application.api.dto;


public sealed interface CartAccessContext
        permits CustomerAccess, GuestAccess {
}
