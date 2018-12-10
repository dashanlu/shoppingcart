package com.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Invoice {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    static {
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    private double totalPrice;

    public Invoice(final double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String formatPrice() {
        return decimalFormat.format(this.totalPrice);
    }
}
