package com.designpattern.pluggableselector;

//Pluggable Selector - 1️⃣ Selector interface’i
interface PaymentSelector {
    PaymentMethod select(String type);
}
