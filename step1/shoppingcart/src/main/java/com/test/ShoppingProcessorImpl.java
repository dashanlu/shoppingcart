package com.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.UUID;

public class ShoppingProcessorImpl implements ShoppingProcessor {
    private Map<UUID, Product> inventory;
    private DecimalFormat decimalFormat;

    public ShoppingProcessorImpl(final Map<UUID, Product> inventory) {
        this.inventory = inventory;

    }

    @Override
    public Invoice process(final ShoppingCart shoppingCart) {
        double totalPrice = shoppingCart.getShoppingList().entrySet().stream()
                                        .map(entry -> entry.getKey().getPrice() * entry.getValue())
                                        .mapToDouble(Double::doubleValue).sum();

        return new Invoice(totalPrice);
    }
}
