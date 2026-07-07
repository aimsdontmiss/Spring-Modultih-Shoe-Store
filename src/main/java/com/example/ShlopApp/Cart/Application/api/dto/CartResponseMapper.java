package com.example.ShlopApp.Cart.Application.api.dto;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartItemId;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;
import org.springframework.stereotype.Component;

@Component
public class CartResponseMapper {
    //    For the Cart model...
//    public CartDto toCartDto(Cart cart) {
//        return new CartDto(
//                cart.getCartId().cartId(),
//                cart.getOwnerId().cartId(),
//                cart.getItems(),
//                cart.createdAt,
//                cart.updatedAt
//        );
//    }

    public CartDto toCartDto(Cart cart) {
        return new CartDto(
                cart.getCartId().cartId(),
                cart.getOwnerId().ownerId(),
                cart.getItems().stream()
                        .toList(),
                cart.getTotalPrice(),
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }

    public Cart toCartDomain(CartDto dto) {
        return new Cart(
                CartId.of(dto.cartId()),
                OwnerId.of(dto.ownerId()),
                dto.items(),
                dto.createdAt(),
                dto.updatedAt()
        );
    }


    //    For the CartItem model...
    public CartItemDto toCartItemDto(CartItem item) {
        return new CartItemDto(
                item.getCartItemId().cartItemId(),
                item.getVariantId(),
                item.getUnitPrice(),
                item.getQuantity()
        );
    }

    public CartItem toCartItemDomain(CartItemDto dto) {
        return new CartItem(
                CartItemId.of(dto.cartItemId()),
                dto.variantId(),
                dto.unitPrice(),
                dto.quantity()
        );
    }
}
