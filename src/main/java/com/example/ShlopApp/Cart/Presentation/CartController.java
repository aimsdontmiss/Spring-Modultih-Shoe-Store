package com.example.ShlopApp.Cart.Presentation;

import com.example.ShlopApp.Cart.Application.api.dto.CartDto;
import com.example.ShlopApp.Cart.Application.api.dto.CartResponseMapper;
import com.example.ShlopApp.Cart.Application.internals.command.AddToCartCommand;
import com.example.ShlopApp.Cart.Application.internals.command.CreateCartCommand;
import com.example.ShlopApp.Cart.Application.internals.interactor.AddToCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.interactor.CreateCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.interactor.GetCartUseCase;
import com.example.ShlopApp.Cart.Application.internals.query.GetCartQuery;
import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.OwnerId;
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
    
    public CartController(
            AddToCartUseCase addToCartUseCase,
            CreateCartUseCase createCartUseCase,
            GetCartUseCase getCartUseCase
    ) {
        this.addToCartUseCase = addToCartUseCase;
        this.createCartUseCase = createCartUseCase;
        this.getCartUseCase = getCartUseCase;       
    }

    @GetMapping("/session")
    public ResponseEntity<String> getSession(HttpSession session) {
        return ResponseEntity.ok(session.getId());
    }

    @GetMapping("/get")
    public ResponseEntity<Cart> getCart(@RequestBody GetCartQuery query) {
        return ResponseEntity.ok(getCartUseCase.execute(query));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartCommand command) {
        return ResponseEntity.ok(addToCartUseCase.execute(command));
    }

    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(HttpSession session) {
        System.out.println("Controller owner = " + session.getId());

        CreateCartCommand command = new CreateCartCommand(OwnerId.of(session.getId()));

        return ResponseEntity.ok(
                mapper.toCartDto(createCartUseCase.execute(command))
        );
    }

}
