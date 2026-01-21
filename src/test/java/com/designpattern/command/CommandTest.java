package com.designpattern.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
TDD’de Command Pattern, özellikle
    davranışı test etmek,
    side-effect’leri izole etmek ve
    if–else patlamasını önlemek için çok temiz bir araçtır.

Kent Beck bakışıyla: önce test, sonra en basit komut.

✅ Command ile
    Her davranış ayrı test
    Yeni işlem → yeni sınıf
    Mevcut kod bozulmaz
    Kent Beck:
    “Add a test, then make it work.”

  9️⃣ Kent Beck perspektifi
        Command Pattern → Testability
        Her Command → tek sorumluluk
        Fixture küçük = Test Setup (Testten önce kurulan ortam, Testin varsaydığı dünya)
        External fixture yok
 */

public class CommandTest {
    @Test
    void shouldWithdrawMoney() {
        Account account = new Account(100);
        Command command = new WithdrawCommand(account, 40);

        command.execute();

        assertEquals(60, account.getBalance());
    }

    @Test
    void shouldDepositMoney() {
        Account account = new Account(100);
        Command command = new DepositCommand(account, 50);

        command.execute();

        assertEquals(150, account.getBalance());
    }


}
