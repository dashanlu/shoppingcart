package com.test;



import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    @Test
    void generateProductGivenValidParameters() {
        final String productName = "test product name";
        final double productPrice = 1;

        final Product product = Product.create(productName, productPrice);

        assertNotNull(product.getId());
        assertEquals(productName, product.getName());
        assertEquals(productPrice, product.getPrice(), 0.001);
    }

    @Test
    void throwExceptionGivenInvalidParameters() {
        final String productName = "test product name";
        final float productPrice = -1;

        assertThrows(IllegalArgumentException.class, () -> Product.create(productName, productPrice));
        assertThrows(IllegalArgumentException.class, () -> Product.create(null, productPrice));
        assertThrows(IllegalArgumentException.class, () -> Product.create("   ", productPrice));
    }


}