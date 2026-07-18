package com.example.ShlopApp.Cart.Application.api.dto;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartResponseMapper {

    public CartDto toCartDto(Cart cart) {
        return new CartDto(
                cart.getCartId().cartId(),
                cart.getOwnerId().getOwnerId(),
                cart.getItems().stream().map(this::toCartItemDto).toList(),
                cart.getTotalPrice(),
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }
//
//    public Cart toCartDomain(CartDto dto) {
//        return new Cart(
//                CartId.of(dto.cartId()),
//                new SessionOwner(dto.value()),
//                dto.items(),
//                dto.createdAt(),
//                dto.updatedAt()
//        );
//    }
//

    //    For the CartItem model...
    public CartItemDto toCartItemDto(CartItem item) {
        return new CartItemDto(
//                item.getCartItemId().cartItemId(),
                item.getVariantId(),
                item.getUnitPrice(),
                item.getQuantity()
        );
    }
//
//    public CartItem toCartItemDomain(CartItemDto dto) {
//        return new CartItem(
//                CartItemId.of(dto.cartItemId()),
//                dto.variantId(),
//                dto.unitPrice(),
//                dto.quantity()
//        );
//    }
}
