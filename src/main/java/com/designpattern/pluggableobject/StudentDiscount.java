package com.designpattern.pluggableobject;

//2️⃣ Farklı davranışlar küçük nesneler olur
class StudentDiscount implements DiscountPolicy {
    public int apply(int amount) {
        return amount - 10;
    }
}
