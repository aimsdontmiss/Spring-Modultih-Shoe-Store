package com.example.ShlopApp.Commerce.Domain.Order.port;

import com.example.ShlopApp.Commerce.Domain.Order.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepoPort {
    void save(Order order);
    Optional<Order> findById(UUID orderId);
    void delete(Order order);
}
