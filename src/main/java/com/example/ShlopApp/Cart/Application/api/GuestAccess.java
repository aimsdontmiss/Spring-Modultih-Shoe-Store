package com.example.ShlopApp.Cart.Application.api;


public record GuestAccess(String guestId)
        implements CartAccessContext {
}
