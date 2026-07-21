package com.example.ShlopApp.Commerce.Domain.Order.port;

import com.example.ShlopApp.Commerce.Domain.Order.model.OrderLine;

public interface OrderLineRepoPort {
    void save(OrderLine orderLine);
    void delete(OrderLine orderLine);
    void deleteAllByOrderId(Long orderId);
}
