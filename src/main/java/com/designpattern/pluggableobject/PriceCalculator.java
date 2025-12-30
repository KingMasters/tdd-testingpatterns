package com.designpattern.pluggableobject;

//3️⃣ Asıl sınıf davranışı dışarıdan alır
//👉 İşte bu Pluggable Object
class PriceCalculator {

    private final DiscountPolicy discountPolicy;

    PriceCalculator(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    int calculate(int amount) {
        return discountPolicy.apply(amount);
    }

    //Kötü Hali
    /*
    int calculate(int amount, boolean isStudent) {

        👉 Sorun
            Davranış flag’e bağlı
            Yeni kural gelirse if büyür
            Testler karmaşıklaşır

        if (isStudent) {
            return amount - 10;
        }
        return amount;
    }
    */
}

