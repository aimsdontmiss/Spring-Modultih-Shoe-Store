package com.example.ShlopApp.Commerce.Infrastructure.adapter;

import com.example.ShlopApp.Commerce.Domain.Order.model.Order;
import com.example.ShlopApp.Commerce.Domain.Order.port.OrderRepoPort;
import com.example.ShlopApp.Commerce.Infrastructure.persistence.OrderEntity;
import com.example.ShlopApp.Commerce.Infrastructure.persistence.OrderPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderRepoAdapter implements OrderRepoPort {

    private final JpaOrderRepository repository;
    private final OrderPersistenceMapper mapper = new OrderPersistenceMapper();

    public OrderRepoAdapter(JpaOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Order order) {
        repository.save(mapper.toOrderEntity(order));
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        Optional<OrderEntity> entity = repository.findById(orderId);
        if (entity.isPresent()) {
            return Optional.ofNullable(mapper.toOrderDomain(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Order order) {
        repository.delete(mapper.toOrderEntity(order));
    }
}
