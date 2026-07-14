package com.example.ShlopApp.Cart.Presentation;

import com.example.ShlopApp.Cart.Application.api.dto.CartDto;
import com.example.ShlopApp.Cart.Application.api.dto.CartResponseMapper;
import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.CreateCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.RemoveItemCommand;
import com.example.ShlopApp.Cart.Application.internals.interactor.AddToCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.interactor.CreateCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.interactor.GetCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.interactor.RemoveFromCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.query.GetCartQuery;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
class CartController {
    private final CartResponseMapper mapper = new CartResponseMapper();

    private final AddToCartUseCase addToCartUseCase;
    private final CreateCartUseCase createCartUseCase;
    private final GetCartUseCase getCartUseCase;
    private final RemoveFromCartUseCase removeFromCartUseCase;
    
    public CartController(
            AddToCartUseCase addToCartUseCase,
            CreateCartUseCase createCartUseCase,
            GetCartUseCase getCartUseCase,
            RemoveFromCartUseCase removeFromCartUseCase
    ) {
        this.addToCartUseCase = addToCartUseCase;
        this.createCartUseCase = createCartUseCase;
        this.getCartUseCase = getCartUseCase;
        this.removeFromCartUseCase = removeFromCartUseCase;
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
    public ResponseEntity<CartDto> addToCart(
            @RequestBody RemoveItemCommand command
    ) {
        return ResponseEntity.ok(
                mapper.toCartDto(removeFromCartUseCase.execute(command))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(
            HttpSession session
    ) {
        System.out.println("Controller owner = " + session.getId());

        CreateCartCommand command = new CreateCartCommand(SessionOwner.of(session.getId()));

        return ResponseEntity.ok(
                mapper.toCartDto(createCartUseCase.execute(command))
        );
    }

}
