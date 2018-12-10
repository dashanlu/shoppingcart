package com.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final static ShoppingCart EMPTY_SHOPPING_CART = new ShoppingCart(Collections.emptyMap());

    private Map<Product, Integer> shoppingList;

    private ShoppingCart(final Map<Product, Integer> shoppingList) {
        this.shoppingList = Collections.unmodifiableMap(shoppingList);
    }

    public Map<Product, Integer> getShoppingList() {
        return this.shoppingList;
    }

    public static ShoppingCart emptyShoppingCart() {
        return EMPTY_SHOPPING_CART;
    }



    public static class ShoppingCartBuilder {
        private Map<Product, Integer> intermediateList;

        public ShoppingCartBuilder() {
            this.intermediateList = new HashMap<>();
        }

        public ShoppingCartBuilder(ShoppingCart existingShoppingCart) {
            this.intermediateList = new HashMap<>(existingShoppingCart.getShoppingList());
        }

        public ShoppingCartBuilder addProduct(final Product product, int quantity) {
            this.intermediateList.compute(product, (uuid, totalNum) -> totalNum == null ? quantity : quantity + totalNum);
            return this;
        }

        public ShoppingCartBuilder empty() {
            this.intermediateList.clear();
            return this;
        }

        public ShoppingCart build() {
            if (this.intermediateList != null && this.intermediateList.size() > 0) {
                return new ShoppingCart(new HashMap<>(this.intermediateList));
            } else {
                return EMPTY_SHOPPING_CART;
            }

        }
    }
}
