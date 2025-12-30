package com.designpattern.pluggableselector;

class CreditCardPayment implements PaymentMethod {
    public int pay(int amount) {
        return amount + 5;
    }
}

