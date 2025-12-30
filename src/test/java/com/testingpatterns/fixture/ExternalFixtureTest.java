package com.testingpatterns.fixture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
External Fixture,
    Kent Beck’in test pattern’larında testin dışında bulunan ve test tarafından kontrol edilmeyen bir ortam veya kaynaktır.
    Fixture test kodunun içinde değil, dış dünyadadır.

    External Fixture nedir?
        Testin çalışması için gereken ama:
            Dosya sistemi
            Veritabanı
            Network
            Sistem saati
            Environment variable
            gibi harici kaynaklara bağımlı olan fixture’lardır.
        Kent Beck bu tür fixture’ları tehlikeli kabul eder çünkü:
            Testler yavaşlar
            Testler flaky (kararsız) olur
            Sonuçlar ortama bağlı değişir
 */
public class ExternalFixtureTest {

    @Test
    void shouldExpireToken() {
        FakeClock clock = new FakeClock(1_000_000L);
        Token token = new Token(clock);

        clock.advance(60_001);

        assertTrue(token.isExpired());
    }
}
