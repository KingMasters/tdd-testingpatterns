package com.designpattern.pluggableselector.reflection;

import java.lang.reflect.Method;

/* 1️⃣ Önce: Davranışlar method olarak
    Burada davranış nesne değil, method olacak.
    Method isimleri = seçim anahtarı.
    Reflection ile Pluggable Selector (if yok)

    Production da kullanmayın! Sadece test amaçlı!
*/
class PaymentService {

    private final String paymentMethodName;

    //Selector logic dışarıdan gelir
    PaymentService(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    int pay(int amount) {
        try {
            Method method =
                    getClass().getMethod(paymentMethodName, int.class);

            return (int) method.invoke(this, amount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // --- davranışlar ---

    public int credit(int amount) {
        return amount + 5;
    }

    public int cash(int amount) {
        return amount;
    }
}


