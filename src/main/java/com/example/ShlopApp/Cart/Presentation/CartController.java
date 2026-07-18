package com.example.ShlopApp.Cart.Presentation;

import com.example.ShlopApp.Cart.Application.api.dto.CartDto;
import com.example.ShlopApp.Cart.Application.api.dto.CartResponseMapper;
import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.DeleteCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.RemoveItemCommand;
import com.example.ShlopApp.Cart.Application.internals.interactor.*;
import com.example.ShlopApp.Cart.Application.internals.query.GetCartQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
class CartController {
    private final CartResponseMapper mapper = new CartResponseMapper();


//    private final SpringSecurityCartOwnerResolver resolver;
    private final AddToCartUseCase addToCartUseCase;
    private final CreateCartUseCase createCartUseCase;
    private final GetCartUseCase getCartUseCase;
    private final RemoveFromCartUseCase removeFromCartUseCase;
    private final DeleteCartUseCase deleteCartUseCase;
    
    public CartController(
            AddToCartUseCase addToCartUseCase,
            CreateCartUseCase createCartUseCase,
            GetCartUseCase getCartUseCase,
            RemoveFromCartUseCase removeFromCartUseCase,
            DeleteCartUseCase deleteCartUseCase
    ) {
        this.addToCartUseCase = addToCartUseCase;
        this.createCartUseCase = createCartUseCase;
        this.getCartUseCase = getCartUseCase;
        this.removeFromCartUseCase = removeFromCartUseCase;
        this.deleteCartUseCase = deleteCartUseCase;
    }


    @GetMapping("/get")
    public ResponseEntity<CartDto> getCart(
            @RequestBody GetCartQuery query
    ) {
        return ResponseEntity.ok(
                mapper.toCartDto(getCartUseCase.execute(query))
        );
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(
            @RequestBody AddToCartCommand command
    ) {
        return ResponseEntity.ok(
                mapper.toCartDto(addToCartUseCase.execute(command))
        );
    }

    @PostMapping("/remove")
    public ResponseEntity<CartDto> removeFromCart(
            @RequestBody RemoveItemCommand command
    ) {
        return ResponseEntity.ok(
                mapper.toCartDto(removeFromCartUseCase.execute(command))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart() {
        return ResponseEntity.ok(
                mapper.toCartDto(createCartUseCase.execute())
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteCart(
            @RequestBody DeleteCartCommand command
    ) {
        deleteCartUseCase.execute(command);
        return ResponseEntity.ok().build();
    }
}
