package com.testingpatterns.crashtestedummy;

import com.testingpatterns.crashtestdummy.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrashTestDummy {
    /*
    Crash test dummy: Sistemin sınırlarını zorlamak için kullanılan bilinçli olarak kurban edilen sahte veri / sahte kullanıcı / sahte nesne
    * Load test’te sahte kullanıcılar
    * Hata fırlatması için özel hazırlanmış input
    * TDD’de bilinçli olarak “ezilen” fake objeler

    Neden bu Crash Test Dummy?
        Logger kullanılmıyor
        Doğru çağrıldı mı umurumuzda değil
        Sadece constructor patlamasın diye var
        Çarpışmada “ölecek” ama sistem test edilecek

    Senaryo
        Bir servis Logger bekliyor
        Bu testte log’un hiçbir önemi yok
        Sadece metot imzası dolsun diye bir nesne lazım
   */
    @Test
    void throwsExceptionWhenAmountIsZero() {
        PaymentService service =
                new PaymentService(new DummyLogger());

        assertThrows(
                IllegalArgumentException.class,
                () -> service.pay(0)
        );
    }
}
