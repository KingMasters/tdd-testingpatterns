package com.designpattern.nullobject;

import org.junit.jupiter.api.Test;

/*
    TDD yaklaşımında Null Object,
     null kontrolünü testlerden ve production kodundan temizlemek için kullanılan davranışsal bir tekniktir.
Klasik GoF Null Object Pattern’ın, test odaklı ve minimal kullanımıdır.

Amaç:
    if (x != null) yazmamak
    Testleri daha sade ve niyeti açık yapmak

 Null Object nedir?
    Gerçek nesnenin yerine geçen,
    hiçbir şey yapmayan ama aynı davranışı sergileyen bir nesnedir.
    * null değildir
    * Aynı interface’i uygular
    * Default / zararsız davranış döndürür

    Kent Beck neden sever?
        TDD perspektifinden:
        ✅ Test setup sadeleşir
        ✅ Branch sayısı azalır
        ✅ Edge case’ler ayrı test olur
        ✅ Testte mock yazma ihtiyacı azalır

     Kent Beck:
        “If you need a special case, make it an object.”

        Null Object ne zaman kullanılır?
            ✅ Kullan
            Optional dependency varsa
            Davranış opsiyonel ise
            null check her yerde çıkıyorsa
            Testlerde mock kurmak anlamsızsa

            ❌ Kullanma
            Hata saklıyorsa
            Zorunlu dependency ise
            Sessizce geçmesi bug yaratıyorsa

        TDD’de sık kullanılan Null Object örnekleri
            NullLogger
            NullNotifier
            NullCache
            NullMetrics
            NullEventPublisher
            Null Object vs Mock vs Stub

            Tür	    Amaç	                    Davranış
            Null    Object	Hiçbir şey yapma	Sabit
            Stub	Veri döndürme	            Sabit
            Mock	Etkileşim doğrulama	        Beklenti

            Kent Beck → önce Null Object, gerekirse Mock.

       Anti-pattern uyarısı 🚨
            public class NullUser extends User {
            }
            ❌ Inheritance ile domain’i bozma
            ❌ Gerçek hataları gizleme

        Daha iyi yaklaşım
            Interface + Null implementasyon
            Açık isimlendirme (NullLogger)

        Özet
            Null Object = null yerine nesne
            TDD’de sadelik ve okunabilirlik
            Design pattern değil, pragmatik teknik
            Kent Beck: testi rahatlatıyorsa kullan
 */
public class NullObjectTest {
    @Test
    void shouldPlaceOrderWithoutLogger() {
        OrderService service =
                new OrderService(new NullLogger());

        service.placeOrder(); // exception atmamalı
    }

}
