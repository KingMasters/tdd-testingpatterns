package com.designpattern.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Value Object’ler: DDD Pattern (building block)
    ✅ Immutable olur
    ✅ Side-effect yoktur
    ✅ External fixture içermez
    ✅ Testi aşırı kolaydır
  Kent Beck’in sevdiği şeyler 😄

  Martin Fowler bile şunu der:
        “Value Objects are one of the most powerful tools for designing testable systems.”

  Value Object tanımı (öz)
    Bir nesne:
        Kimlik taşımaz
        Değeriyle eşitlenir
        Değiştirilemez (immutable)

    TDD’ye cuk oturuyor?
        Test setup çok küçük
        Fixture = sadece constructor
        Mock yok
        Fake yok
        Zaman yok
        IO yok

Ne zaman Value Object kullanmalısın?
    Para, tarih aralığı, email, koordinat
    Kurallar değerin üzerinde
    Primitive obsession varsa
    ❌ Sadece veri taşıyorsa → DTO

Özet
    Value Object = DDD kavramı
    TDD ile doğal müttefik
    Test yazmayı kolaylaştırır
 */
public class ValueObjectTest {

    @Test
    void moneyWithSameAmountAndCurrencyShouldBeEqual() {
        Money fiveUsd1 = new Money(5, "USD");
        Money fiveUsd2 = new Money(5, "USD");

        assertEquals(fiveUsd1, fiveUsd2);
    }

}
