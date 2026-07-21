package com.example.ShlopApp.Commerce.Infrastructure.adapter;

import com.example.ShlopApp.Commerce.Infrastructure.persistence.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface JpaOrderLineRepository extends JpaRepository<OrderLineEntity, UUID> {
}
