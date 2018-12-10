package com.test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingProcessorImplTest {
    final private Product product1 = Product.create("product1", 23.33);
    final private Product product2 = Product.create("product1", 3.33);
    final private Map<UUID, Product> inventory = Stream.of(
        new AbstractMap.SimpleEntry<>(product1.getId(), product1),
        new AbstractMap.SimpleEntry<>(product2.getId(), product2)
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    private final ShoppingProcessor shoppingProcessor = new ShoppingProcessorImpl(inventory);

    @Test
    public void sumPriceForEachProductInShoppingCart() {
        final ShoppingCart mockShoppingCart = mock(ShoppingCart.class);

        final Map<Product, Integer> shoppingList = Stream.of(
            new AbstractMap.SimpleEntry<>(product1, 10),
            new AbstractMap.SimpleEntry<>(product2, 10)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        when(mockShoppingCart.getShoppingList()).thenReturn(shoppingList);
        double totalPrice = shoppingProcessor.process(mockShoppingCart).getTotalPrice();
        assertEquals(266.60, totalPrice, 0.001);
    }

}