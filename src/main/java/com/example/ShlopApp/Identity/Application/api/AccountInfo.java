package com.example.ShlopApp.Identity.Application.api;

import java.util.UUID;

public record AccountInfo(
        UUID accountId,
        String username
) {}
