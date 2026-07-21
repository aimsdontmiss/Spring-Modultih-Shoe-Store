package com.example.ShlopApp.Commerce.Application.api.dto;

import com.example.ShlopApp.Commerce.Domain.Order.model.ValueObjects.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID ownerId,
        List<OrderLineDto> lineItems,
        BigDecimal total,
        OrderStatus status,
        Instant createdAt
) {
}
