package com.designpattern.pluggableselector.reflection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    ✔️ if yok
    ✔️ selector class yok
    ✔️ minimal kod
 */
public class PluggableSelectorReflectionTest {
    @Test
    void creditPaymentAddsFee() {
        PaymentService service =
                new PaymentService("credit");

        assertEquals(105, service.pay(100));
    }

    @Test
    void cashPaymentHasNoFee() {
        PaymentService service =
                new PaymentService("cash");

        assertEquals(100, service.pay(100));
    }

}
