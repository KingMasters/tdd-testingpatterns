package com.designpattern.pluggableselector;

//✔️ Service temiz
//✔️ Karar mekanizması plug edilebilir
class PaymentService {
    private final PaymentSelector selector;

    PaymentService(PaymentSelector selector) {
        this.selector = selector;
    }

    int pay(String type, int amount) {
        return selector.select(type).pay(amount);
    }

    /*Kötü başlangıç (if/switch ile)

    int pay(String type, int amount) {
            👉 Sorunlar
            Seçim mantığı business kodunun içinde
            Yeni tip → yeni if
            Testler karar + davranış karışık

        if (type.equals("CREDIT")) {
            return amount + 5;
        } else if (type.equals("CASH")) {
            return amount;
        }
        throw new IllegalArgumentException();
    }
    */

}
