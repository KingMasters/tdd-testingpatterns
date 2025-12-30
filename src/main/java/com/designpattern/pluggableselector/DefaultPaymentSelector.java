package com.designpattern.pluggableselector;

// Pluggable Selector-  2️⃣ Varsayılan selector (if burada izole)
class DefaultPaymentSelector implements PaymentSelector {

    public PaymentMethod select(String type) {
        if (type.equals("CREDIT")) {
            return new CreditCardPayment();
        }
        if (type.equals("CASH")) {
            return new CashPayment();
        }
        throw new IllegalArgumentException();
    }
}
