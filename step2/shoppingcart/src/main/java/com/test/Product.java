package com.test;

import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class Product {
    private UUID id;
    private String name;
    private double price;

    private Product(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Product create(final String name, final double price) {
        if (StringUtils.isNotBlank(name) && price >= 0) {
            return new Product(UUID.randomUUID(), name, price);
        }
        throw new IllegalArgumentException(name + " " + price);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
