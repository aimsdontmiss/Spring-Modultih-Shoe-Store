package com.example.ShlopApp.Identity.Application.internals.command;

public record LoginCommand(
        String username,
        String password
) {
}
