package com.testingpatterns.logstring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
1️⃣ Metot çağrı sırası önemliyse
Birden fazla callback varsa ve hangi sırayla çağrıldığını doğrulamak istiyorsan.
start → process → finish
Tek assert ile:
assertEquals("start process finish ", log);

2️⃣ Etkileşim sayısı çok azsa
1–3 metot
Basit akış
Karmaşık koşul yok
“Bu testte mock framework kullanmak fazla olur” dediğin an.

3️⃣ Mock framework kullanmak istemiyorsan
Kent Beck yaklaşımı:
Mock → sonra
Basit string → şimdi
Özellikle:
Eğitim amaçlı
İlk TDD döngüsünde (Red–Green)

4️⃣ Testi hızlı yazmak istiyorsan
Fake class yok
Verify yok
Setup yok
log += "called ";
bitti.

5️⃣ Self-Shunt ile birlikte
Test class listener olur + log tutar:
Ayrı mock yok
Her şey test class’ta
Bu ikili Kent Beck’in klasik kombinasyonudur.
 */

public class LogStringTest {
    private String log = "";

    @Test
    void callsMethodsInOrder() {
        Listener listener = new Listener() {
            @Override
            public void start() {
                log += "start ";
            }

            @Override
            public void finish() {
                log += "finish ";
            }
        };

        Processor processor = new Processor(listener);

        processor.process();

        assertEquals("start finish ", log);
    }
}

