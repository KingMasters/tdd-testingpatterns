package com.designpattern.pluggableobject;

//2️⃣ Farklı davranışlar küçük nesneler olur
class NoDiscount implements DiscountPolicy {
    public int apply(int amount) {
        return amount;
    }
}



