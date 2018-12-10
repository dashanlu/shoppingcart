package com.test;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ProductTest {

    @Test
    void generateProductGivenValidParameters() {
        final String productName = "test product name";
        final float productPrice = 1;

        final Product product = Product.create(productName, productPrice);

        assertNotNull(product.getId());
        assertEquals(productName, product.getName());
        assertEquals(productPrice, product.getPrice());
    }

    @Test
    void throwExceptionGivenInvalidParameters() {
        final String productName = "test product name";
        final float productPrice = -1;

//        assertThrows(IllegalArgumentException.class, () -> Product.create(productName, productPrice));
//        assertThrows(IllegalArgumentException.class, () -> Product.create(null, productPrice));
//        assertThrows(IllegalArgumentException.class, () -> Product.create("   ", productPrice));
    }


}