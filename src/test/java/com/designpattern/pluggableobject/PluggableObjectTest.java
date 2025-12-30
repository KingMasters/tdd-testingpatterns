package com.designpattern.pluggableobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Pluggable Object : Davranışı değiştirilebilir (tak-çıkar) hale getirmek için kullanılan
    çok temel ama güçlü bir tekniktir.
    Amaç “büyük pattern’ler” değil, testi ve tasarımı basitleştirmektir.

    1️⃣ Pluggable Object nedir?
        Davranışı, sınıfın içine gömmek yerine dışarıdan verilen bir nesneye bırakmaktır.
        Yani:
        if / else
        switch
        flag’ler yerine:
    ➡️ küçük nesneler takılır (plug edilir)
        Kent Beck buna bazen:
        Pluggable Object
        bazen de Strategy’nin TDD’deki sade hali

    2️⃣ Kent Beck neden kullanır?
        🎯 Ana amaçlar
        1- Testi kolaylaştırmak
        2- Koşullu mantığı (if/else) azaltmak
        3- Değişen davranışı izole etmek
        4- Erken genellemeden kaçınmak
    “Önce çalışan en basit şey, sonra duplication, sonra abstraction.”
        Pluggable Object, abstraction’ı geç ve güvenli yapmanı sağlar.

   3️⃣ Ne zaman kullanılır?
        Kent Beck’e göre Pluggable Object genelde şu anda ortaya çıkar:
        Testte aynı testin farklı varyasyonlarını yazmaya başladığında
        if (x) doA else doB gördüğünde
        Aynı algoritma ama farklı kural / politika varsa

   7️⃣ Kent Beck bakış açısı (önemli)
        Kent Beck şunu özellikle vurgular:
        “Pluggable Object, Strategy pattern değildir.
        Strategy olabilir ama TDD’de ihtiyaçtan doğar, kitap için yapılmaz.”
        Yani:
        Başta interface yazmak zorunda değilsin
        Gerekince çıkar
        Küçük adımlarla refactor edilir

    8️⃣ TDD akışında Pluggable Object
        1- Test yaz → if ile çalıştır
        2- İkinci benzer test → duplication fark edilir
        3- Davranış nesneye çıkarılır
        4- Nesne plug edilir
        ➡️ Refactor aşamasında

    9️⃣ Pluggable Object vs diğerleri
        Kavram	            Fark
        Strategy Pattern	Daha “resmi” ve baştan tasarlanır
        Pluggable Object	TDD sırasında doğal çıkar
        Null Object	        Boş davranış için özel bir plug
        Command	            Davranış + state + zaman

    10️⃣ Özet
        Neden?
        → Testleri sadeleştirmek, if’lerden kurtulmak
        Amaç?
        → Değişen davranışı izole etmek
        Nasıl?
        → Davranışı küçük nesneye çıkar, constructor’dan ver
        Kent Beck tarzı
        → Küçük, geç, ihtiyaç oldukça

 */

public class PluggableObjectTest {
    /*
        ✔️ Flag yok
        ✔️ if yok
        ✔️ Testler çok net
     */

    @Test
    void studentGetsDiscount() {
        PriceCalculator calculator =
                new PriceCalculator(new StudentDiscount());

        assertEquals(90, calculator.calculate(100));
    }

    @Test
    void regularCustomerGetsNoDiscount() {
        PriceCalculator calculator =
                new PriceCalculator(new NoDiscount());

        assertEquals(100, calculator.calculate(100));
    }


}


