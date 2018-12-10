package com.test;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartTest {

    @Test
    @DisplayName("Empty shopping cart is a singleton object")
    void emptyShoppingCartIsSingleton() {
        final ShoppingCart empty1 = ShoppingCart.emptyShoppingCart();
        final ShoppingCart empty2 = ShoppingCart.emptyShoppingCart();
        assertSame(empty1, empty2);
    }

    @Test
    @DisplayName("The ShoppingCartBuilder returns an empty shopping cart given nothing")
    void createEmptyShoppingCartGivenNothing() {
        final ShoppingCart.ShoppingCartBuilder builder = new ShoppingCart.ShoppingCartBuilder();

        assertEquals(ShoppingCart.emptyShoppingCart(), builder.build());
    }

    @Test
    @DisplayName("The ShoppingCartBuilder returns a shopping cart with product id and its quantity given product object and its quantity")
    void createAShoppingCartGivenProductAndQuantity() {
        final Product productMock = mock(Product.class);
        final UUID testUUID = UUID.randomUUID();
        when(productMock.getId()).thenReturn(testUUID);
        when(productMock.getName()).thenReturn("testName");
        when(productMock.getPrice()).thenReturn(1d);

        final ShoppingCart.ShoppingCartBuilder builder = new ShoppingCart.ShoppingCartBuilder();
        final ShoppingCart shoppingCart = builder.addProduct(productMock, 10).build();

        assertEquals(Integer.valueOf(10), shoppingCart.getShoppingList().get(productMock));
    }

    @Test
    @DisplayName("The ShoppingCartBuilder update quantity of product given another amount of the product with the same product ID ")
    void updateProductTotalNum() {
        final Product productMock = mock(Product.class);
        final UUID testUUID = UUID.randomUUID();
        when(productMock.getId()).thenReturn(testUUID);
        when(productMock.getName()).thenReturn("testName");
        when(productMock.getPrice()).thenReturn(1d);

        final ShoppingCart.ShoppingCartBuilder builder = new ShoppingCart.ShoppingCartBuilder();
        final ShoppingCart shoppingCart = builder.addProduct(productMock, 10)
                                                 .addProduct(productMock, 20).build();

        assertEquals(Integer.valueOf(10 + 20), shoppingCart.getShoppingList().get(productMock));
    }

    @Test
    @DisplayName("The ShoppingCartBuilder can be initialized given an existing ShoppingCart")
    void instantiateABuilderWithAnExistingShoppingCart() {
        final Map<Product, Integer> mockShoppingList = Collections.singletonMap(mock(Product.class), 1000);
        final ShoppingCart mockShoppingCart = mock(ShoppingCart.class);
        when(mockShoppingCart.getShoppingList()).thenReturn(mockShoppingList);

        final ShoppingCart.ShoppingCartBuilder builder = new ShoppingCart.ShoppingCartBuilder(mockShoppingCart);
        final ShoppingCart shoppingCart = builder.build();

        assertEquals(mockShoppingCart.getShoppingList(), shoppingCart.getShoppingList());
    }

    @Test
    @DisplayName("The ShoppingCartBuilder is emptied itself when calling its empty() method")
    void emptyShoppingBuilderWhenCallingReset() {
        final Map<Product, Integer> mockShoppingList = Collections.singletonMap(mock(Product.class), 1000);
        final ShoppingCart mockShoppingCart = mock(ShoppingCart.class);
        when(mockShoppingCart.getShoppingList()).thenReturn(mockShoppingList);

        final ShoppingCart emptyShoppingCart = new ShoppingCart.ShoppingCartBuilder(mockShoppingCart).empty().build();

        assertEquals(0, emptyShoppingCart.getShoppingList().size());
    }
}