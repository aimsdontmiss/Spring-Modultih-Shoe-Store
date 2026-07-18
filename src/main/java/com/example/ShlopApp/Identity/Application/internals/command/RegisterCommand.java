package com.example.ShlopApp.Identity.Application.internals.command;

public record RegisterCommand(
        String email,
        String username,
        String password
//        Set<Role> roles
) { }
