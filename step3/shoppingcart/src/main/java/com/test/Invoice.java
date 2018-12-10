package com.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Invoice {

    private double totalPrice;
    private double saleTax;

    public Invoice(final double totalPrice, final double saleTax) {
        this.totalPrice = totalPrice;
        this.saleTax = saleTax;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getSaleTax() {
        return saleTax;
    }

    public static String formatWithDecimalplace(final double number) {
        BigDecimal bigDecimal = BigDecimal.valueOf(number);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.toPlainString();
    }
}
