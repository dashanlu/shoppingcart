package com.test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    private final ShoppingProcessor shoppingProcessor = new ShoppingProcessorImpl(inventory, 0.1);

    @Test
    @DisplayName("The shopping processor sum the prices for each product and calculate the sale tax on total price ")
    void sumPriceForEachProductInShoppingCartAndCalculateSaleTaxGivenTaxRate() {
        final ShoppingCart mockShoppingCart = mock(ShoppingCart.class);

        final Map<Product, Integer> shoppingList = Stream.of(
            new AbstractMap.SimpleEntry<>(product1, 10),
            new AbstractMap.SimpleEntry<>(product2, 10)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        when(mockShoppingCart.getShoppingList()).thenReturn(shoppingList);
        final Invoice invoice = shoppingProcessor.process(mockShoppingCart);
        final double totalPrice = invoice.getTotalPrice();
        final double saleTax = invoice.getSaleTax();
        assertEquals(293.26, totalPrice, 0.001);
        assertEquals(26.66, saleTax, 0.001);
    }

}