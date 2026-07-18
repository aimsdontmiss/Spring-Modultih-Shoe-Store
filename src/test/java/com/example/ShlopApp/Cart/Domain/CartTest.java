package com.example.ShlopApp.Cart.Domain;

import com.example.ShlopApp.Cart.Domain.model.Cart;
import com.example.ShlopApp.Cart.Domain.model.CartItem;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CartOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.CustomerOwner;
import com.example.ShlopApp.Cart.Domain.model.ValueObjects.SessionOwner;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CartTest {

    public CartOwner createSessionOwner() {
        return new SessionOwner(UUID.randomUUID());
    }

    public CartOwner createCustomerOwner() {
        return new CustomerOwner(UUID.fromString("859a7c39-ce76-4c13-a0f5-5c75aea5d17d"));
    }

    // CART TESTS ON SESSION (ANON) CART OWNERS
    @Test
    void shouldCreateEmptySessionCart() {

        CartOwner owner = createSessionOwner();

        Cart cart = Cart.create(owner);

        assertNotNull(cart);
        assertTrue(cart.getItems().isEmpty());
        assertEquals(owner, cart.getOwnerId());
    }

    @Test
    void shouldAddItemToEmptySessionCart() {

        CartOwner owner = createSessionOwner();
        Cart cart = Cart.create(owner);

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.valueOf(100),
                2
        );

        assertEquals(1, cart.getItems().size());

        CartItem item = cart.getItems().get(0);

        assertEquals(variantId, item.getVariantId());
        assertEquals(2, item.getQuantity());
    }

    @Test
    void shouldIncreaseQuantityWhenAddingExistingSessionVariant() {

        Cart cart = Cart.create(createSessionOwner());

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                1
        );

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        assertEquals(1, cart.getItems().size());
        assertEquals(
                3,
                cart.getItems().get(0).getQuantity()
        );
    }

    @Test
    void shouldAddDifferentSessionVariantsSeparately() {

        Cart cart = Cart.create(createSessionOwner());

        cart.addToCart(UUID.randomUUID(), BigDecimal.TEN, 1);
        cart.addToCart(UUID.randomUUID(), BigDecimal.TEN, 1);

        assertEquals(2, cart.getItems().size());
    }

    @Test
    void shouldCalculateSessionTotalPrice() {

        Cart cart = Cart.create(createSessionOwner());

        cart.addToCart(
                UUID.randomUUID(),
                BigDecimal.valueOf(20),
                2
        );

        assertEquals(
                BigDecimal.valueOf(40),
                cart.getTotalPrice()
        );
    }

    @Test
    void emptySessionCartShouldHaveZeroTotal() {

        Cart cart = Cart.create(createSessionOwner());

        assertEquals(
                BigDecimal.ZERO,
                cart.getTotalPrice()
        );
    }

    @Test
    void shouldDecreaseQuantityWhenRemovingPartOfSessionItem() {

        Cart cart = Cart.create(createSessionOwner());

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                5
        );

        cart.removeFromCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        assertEquals(
                3,
                cart.getItems().get(0).getQuantity()
        );
    }

    @Test
    void shouldRemoveItemWhenRemovingEntireSessionQuantity() {

        Cart cart = Cart.create(createSessionOwner());

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        cart.removeFromCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void shouldBelongToSessionOwner() {

        CartOwner owner = createSessionOwner();

        Cart cart = Cart.create(owner);

        assertTrue(cart.belongsTo(owner));
    }

    @Test
    void shouldNotBelongToDifferentSessionOwner() {

        Cart cart = Cart.create(createSessionOwner());

        CartOwner otherOwner = createSessionOwner();

        assertFalse(cart.belongsTo(otherOwner));
    }

    @Test
    void shouldThrowWhenRemovingMissingSessionItem() {

        Cart cart = Cart.create(createSessionOwner());

        assertThrows(
                RuntimeException.class,
                () -> cart.removeFromCart(
                        UUID.randomUUID(),
                        BigDecimal.TEN,
                        1
                )
        );
    }

    // CART TESTS ON ACCOUNT (LOGGED) CART OWNERS
    @Test
    void shouldCreateEmptyCustomerCart() {

        CartOwner owner = createCustomerOwner();

        Cart cart = Cart.create(owner);

        assertNotNull(cart);
        assertTrue(cart.getItems().isEmpty());
        assertEquals(owner, cart.getOwnerId());
    }

    @Test
    void shouldAddItemToEmptyCustomerCart() {

        CartOwner owner = createCustomerOwner();
        Cart cart = Cart.create(owner);

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.valueOf(100),
                2
        );

        assertEquals(1, cart.getItems().size());

        CartItem item = cart.getItems().get(0);

        assertEquals(variantId, item.getVariantId());
        assertEquals(2, item.getQuantity());
    }

    @Test
    void shouldIncreaseQuantityWhenAddingExistingCustomerVariant() {

        Cart cart = Cart.create(createCustomerOwner());

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                1
        );

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        assertEquals(1, cart.getItems().size());
        assertEquals(
                3,
                cart.getItems().get(0).getQuantity()
        );
    }

    @Test
    void shouldAddDifferentCustomerVariantsSeparately() {

        Cart cart = Cart.create(createCustomerOwner());

        cart.addToCart(UUID.randomUUID(), BigDecimal.TEN, 1);
        cart.addToCart(UUID.randomUUID(), BigDecimal.TEN, 1);

        assertEquals(2, cart.getItems().size());
    }

    @Test
    void shouldCalculateCustomerTotalPrice() {

        Cart cart = Cart.create(createCustomerOwner());

        cart.addToCart(
                UUID.randomUUID(),
                BigDecimal.valueOf(20),
                2
        );

        assertEquals(
                BigDecimal.valueOf(40),
                cart.getTotalPrice()
        );
    }

    @Test
    void emptyCustomerCartShouldHaveZeroTotal() {

        Cart cart = Cart.create(createCustomerOwner());

        assertEquals(
                BigDecimal.ZERO,
                cart.getTotalPrice()
        );
    }

    @Test
    void shouldDecreaseQuantityWhenRemovingPartOfCustomerItem() {

        Cart cart = Cart.create(createCustomerOwner());

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                5
        );

        cart.removeFromCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        assertEquals(
                3,
                cart.getItems().get(0).getQuantity()
        );
    }

    @Test
    void shouldRemoveItemWhenRemovingEntireCustomerQuantity() {

        Cart cart = Cart.create(createCustomerOwner());

        UUID variantId = UUID.randomUUID();

        cart.addToCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        cart.removeFromCart(
                variantId,
                BigDecimal.TEN,
                2
        );

        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void shouldBelongToCustomerOwner() {

        CartOwner owner = createCustomerOwner();

        Cart cart = Cart.create(owner);

        assertTrue(cart.belongsTo(owner));
    }

    @Test
    void shouldNotBelongToDifferentCustomerOwner() {

        Cart cart = Cart.create(createCustomerOwner());

        CartOwner otherOwner = new CustomerOwner(UUID.fromString("4ffd5d77-b7cc-44da-9e98-42d5d0ca7028"));

        assertFalse(cart.belongsTo(otherOwner));
    }

    @Test
    void shouldThrowWhenRemovingMissingCustomerItem() {

        Cart cart = Cart.create(createCustomerOwner());

        assertThrows(
                RuntimeException.class,
                () -> cart.removeFromCart(
                        UUID.randomUUID(),
                        BigDecimal.TEN,
                        1
                )
        );
    }

}


