package com.example.ShlopApp.Catalog.Application.api;

import java.math.BigDecimal;
import java.util.UUID;

public record VariantDto(
        UUID variantId,
        Long productId,
        String shoeSize,
        BigDecimal price,
        String currency,
        String market,
        boolean available
) {
}
