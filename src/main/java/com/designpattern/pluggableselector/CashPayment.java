package com.designpattern.pluggableselector;

class CashPayment implements PaymentMethod {
    public int pay(int amount) {
        return amount;
    }
}
