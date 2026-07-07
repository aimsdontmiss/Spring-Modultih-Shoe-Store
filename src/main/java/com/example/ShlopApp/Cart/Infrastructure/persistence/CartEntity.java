package com.example.ShlopApp.Cart.Infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String owner;

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
            String owner
    ) {
        this.cartId = cartId;
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    public void touch() {
        this.updatedAt = Instant.now();
    }

}


