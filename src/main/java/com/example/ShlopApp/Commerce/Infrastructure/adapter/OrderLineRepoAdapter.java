package com.example.ShlopApp.Commerce.Infrastructure.adapter;

import com.example.ShlopApp.Cart.Application.api.CartFacade;
import com.example.ShlopApp.Commerce.Domain.Order.model.OrderLine;
import com.example.ShlopApp.Commerce.Domain.Order.port.OrderLineRepoPort;
import com.example.ShlopApp.Commerce.Infrastructure.persistence.OrderPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class OrderLineRepoAdapter implements OrderLineRepoPort {

    private final JpaOrderLineRepository repository;
    private final OrderPersistenceMapper mapper = new OrderPersistenceMapper();

    public OrderLineRepoAdapter(JpaOrderLineRepository repository) {
        this.repository = repository;
    }

    public void save(OrderLine orderLine) {
        repository.save(
                mapper.toOrderLineEntity(orderLine)
        );
    }

    @Override
    public void delete(OrderLine orderLine) {

    }

    @Override
    public void deleteAllByOrderId(Long orderId) {

    }

}
