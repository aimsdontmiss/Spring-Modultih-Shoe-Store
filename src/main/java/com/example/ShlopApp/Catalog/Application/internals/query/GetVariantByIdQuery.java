package com.example.ShlopApp.Catalog.Application.internals.query;

import java.util.UUID;

public record GetVariantByIdQuery(
        UUID variantId
) {
}
