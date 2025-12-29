package com.testingpatterns.selfshunt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Kent Beck’in TDD (Test-Driven Development) yaklaşımında Self-Shunt, testlerde kullanılan bir test double (özellikle stub/mock benzeri) tekniğidir.

Self-Shunt, test sınıfının bizzat kendisinin, test edilen nesnenin bağımlılığı gibi davranmasıdır.
Yani ayrı bir mock/stub sınıfı yazmak yerine, test class’ı ilgili arayüzü (interface) implemente eder ve bu şekilde test edilen sınıfla etkileşir.

Neden kullanılır?
* Testi basit tutmak
* Ekstra sahte (fake/mock) sınıflar yazmaktan kaçınmak
* Test senaryosunu tek yerde, okunur hale getirmek

Ne zaman işe yarar?
* Bağımlılığın davranışı çok basitse
* Testte sadece bir veya iki callback / etkileşim izlenecekse
* “Çağrıldı mı / çağrılmadı mı?” gibi basit doğrulamalar varsa
 */

class SelfShuntTest implements ResultListener {

    private boolean notified = false;

    @Test
    void notifiesListenerOnSuccess() {
        SomeService service = new SomeService(this);
        service.doWork();
        assertTrue(notified);
    }

    @Override
    public void notifySuccess() {
        notified = true;
    }

}
