package com.designpattern.templatemethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
DD yaklaşımında Template Method,
    testleri ve üretim kodunu tekrar eden “iskelet + değişen adımlar” şeklinde ayırmak için kullanılan davranışsal bir tekniktir.

GoF’ta Template Method Pattern olarak geçer;
    Kent Beck bunu erken tasarım değil, refactor aracı olarak görür.
    Önce testleri yaz → tekrar fark et → iskeleti çıkar

Template Method nedir? (özet)
    Algoritmanın değişmeyen iskeleti üst sınıfta
    Değişen adımlar alt sınıflarda
    final bir method, override edilebilen hook’lar

Kent Beck perspektifi (çok önemli)
Kent Beck:
    ❌ “En baştan template çıkarayım” demez
    ✅ “Tekrar oluştu mu? → refactor et” der
    “Duplication is the root of all evil (in design).”

TDD’de Template Method ne için kullanılır?
    Testlerde tekrar eden akışı kaldırmak
    Fixture kurulumunu standardize etmek
    Algorithm skeleton’ını korumak
    Varyasyonları izole etmek
 */

abstract class AccountTestTemplate {

    protected Account account;

    @BeforeEach
    void setUp() {
        account = createAccount();
    }

    @Test
    void withdraw() {
        account.withdraw(10);
        assertBalance();
    }

    //Tekrarlı test akışı yerine AccountTestTemplate kullanıldı
//    @Test
//    void withdrawFromEmptyAccount() {
//        Account account = new Account(0);
//        account.withdraw(10);
//        assertEquals(0, account.getBalance());
//    }

    //Tekrarlı test akışı yerine AccountTestTemplate kullanıldı
//    @Test
//    void withdrawFromPositiveAccount() {
//        Account account = new Account(100);
//        account.withdraw(10);
//        assertEquals(90, account.getBalance());
//    }


    protected abstract Account createAccount();
    protected abstract void assertBalance();
}
