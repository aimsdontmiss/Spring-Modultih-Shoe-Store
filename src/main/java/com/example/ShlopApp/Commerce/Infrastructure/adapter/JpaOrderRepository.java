package com.example.ShlopApp.Commerce.Infrastructure.adapter;

import com.example.ShlopApp.Commerce.Infrastructure.persistence.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface JpaOrderRepository extends JpaRepository<OrderEntity, UUID> {
}
