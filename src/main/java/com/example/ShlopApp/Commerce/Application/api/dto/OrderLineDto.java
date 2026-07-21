package com.example.ShlopApp.Commerce.Application.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderLineDto(
        UUID productId,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) {
}
