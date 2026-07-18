package com.example.ShlopApp.Cart.Infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "STG_CART")
@Data
@NoArgsConstructor
public class CartEntity {

    @Id
    private UUID cartId;

    @Column(name = "OWNER_ID", nullable = false)
    private UUID ownerId;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartItemEntity> items = new ArrayList<>();

    private Instant createdAt = new Date().toInstant();
    private Instant updatedAt = new Date().toInstant();

    public CartEntity(
            UUID cartId,
            UUID ownerId
    ) {
        this.cartId = cartId;
        this.ownerId = ownerId;
        this.items = new ArrayList<>();
    }

    public void touch() {
        this.updatedAt = Instant.now();
    }

}


