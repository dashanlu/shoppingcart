package com.test;

import java.util.Map;
import java.util.UUID;

public class ShoppingProcessorImpl implements ShoppingProcessor {
    private Map<UUID, Product> inventory;
    private double taxRate;

    public ShoppingProcessorImpl(final Map<UUID, Product> inventory, final double taxRate) {
        this.inventory = inventory;
        this.taxRate = taxRate;
    }

    @Override
    public Invoice process(final ShoppingCart shoppingCart) {
        double totalPriceBeforeTax = shoppingCart.getShoppingList().entrySet().stream()
                                                 .map(entry -> entry.getKey().getPrice() * entry.getValue())
                                                 .mapToDouble(Double::doubleValue).sum();

        final double totalPrice = totalPriceBeforeTax * (1 + taxRate);
        return new Invoice(totalPrice, totalPrice - totalPriceBeforeTax);
    }
}
