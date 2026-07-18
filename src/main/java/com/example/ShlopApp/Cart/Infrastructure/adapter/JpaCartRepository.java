package com.example.ShlopApp.Cart.Infrastructure.adapter;

import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Infrastructure.persistence.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface JpaCartRepository extends JpaRepository<CartEntity, UUID> {
    Optional<CartEntity> findByOwnerId(UUID ownerId);
}
