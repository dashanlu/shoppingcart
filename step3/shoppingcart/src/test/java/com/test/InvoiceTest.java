package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceTest {

    @Test
    void manualFormat() {
        assertEquals("0.99", Invoice.formatWithDecimalplace(0.991));
        assertEquals("0.99", Invoice.formatWithDecimalplace(0.992));
        assertEquals("0.99", Invoice.formatWithDecimalplace(0.993));
        assertEquals("0.99", Invoice.formatWithDecimalplace(0.994));
        assertEquals("1.00", Invoice.formatWithDecimalplace(0.995));
        assertEquals("1.00", Invoice.formatWithDecimalplace(0.996));
        assertEquals("1.00", Invoice.formatWithDecimalplace(0.997));
        assertEquals("1.00", Invoice.formatWithDecimalplace(0.998));
        assertEquals("1.00", Invoice.formatWithDecimalplace(0.999));

        assertEquals("1.01", Invoice.formatWithDecimalplace(1.009));
        assertEquals("1.00", Invoice.formatWithDecimalplace(1.004));

        assertEquals("314.95", Invoice.formatWithDecimalplace(314.945));
        assertEquals("40.00", Invoice.formatWithDecimalplace(39.995));
    }
}