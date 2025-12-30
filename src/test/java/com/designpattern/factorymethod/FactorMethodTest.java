package com.designpattern.factorymethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Factory Method (fixture factory)
    📌 Amaç:
        Test senaryosu net
        Setup detayları saklı

    📌 Ne zaman kullanmalısın? (Kent Beck kuralı gibi düşün)
        Factory Method ekle:
        ✔️ 3. kez aynı new’u yazıyorsan
        ✔️ Test okunurluğu düşüyorsa
        ✔️ Nesne türleri çoğalacak hissi varsa
        🚫 Daha ilk testte “ileride lazım olur” diye ekleme


    📌 Kent Beck için Factory Method:
        Bir tasarım deseni değil, bir refactoring adımıdır
        Testleri sadeleştirmek için vardır
        TDD akışının doğal sonucu olarak ortaya çıkar

    📌 Örnek : Money (Dollar, Franc) Factory Method
        Money dollar = Money.dollar(5);
        Money franc = Money.franc(5);
 */
public class FactorMethodTest {
    private Account createAccountWithBalance(int amount) {
        return new Account(amount);
    }

    @Test
    void shouldWithdrawMoney() {
        Account account = createAccountWithBalance(100);

        account.withdraw(40);

        assertEquals(60, account.getBalance());
    }
}
