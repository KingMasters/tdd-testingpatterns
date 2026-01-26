package com.designpattern.imposter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Fake test double’dır; ama Imposter ise çoğu zaman sadece kötü seçilmiş bir kelimedir, genelde Fake anlamında kullanılır.

Imposter (Spy) : Mock/Stub a benzer ama davranışsal olarak daha gelişmiş bir test double türüdür.
        Imposter, gerçek nesnenin davranışlarını taklit eder ve genellikle gerçek nesnenin yerine geçer.
        Imposter'lar, gerçek nesnenin metodlarını çağırabilir ve bu metodların çağrılma sayısını,
        parametrelerini ve dönüş değerlerini izleyebilir.
        Bu sayede, test senaryolarında daha gerçekçi ve kapsamlı testler yapılabilir.
        Imposter'lar, özellikle karmaşık sistemlerde ve entegrasyon testlerinde kullanılır.

        3️⃣ Ne zaman Imposter kullanılır?
        Kent Beck Imposter’ı şu durumlarda önerir:
        🔴 Problem
            Test edilen kod, başka bir nesneyle konuşuyor
            Bu nesne:
                Yavaş (DB, API, IO)
                Yan etkili
                Henüz yazılmamış
            Testte:
                “Şu metod çağrıldı mı?”
                “Doğru parametre gitti mi?”
                “Kaç kere çağrıldı?”
            bunları görmek istiyorsun.

       📌 Burada:
            Gerçek SMTP yok
            Ama servis email gönderdiğini sanıyor

        5️⃣ Mock’tan farkı ne?
            Kent Beck, özellikle frameworksüz TDD’de şu ayrımı yapar:
            Mock Framework      	    Imposter
            --------------              ---------------
            Mockito gibi	            Elle yazılır
            Davranış gizli	            Davranış açık
            DSL ile	                    Düz kod
            Testi framework’e bağlar	Testi sade tutar

        💡 Kent Beck genelde şunu tercih eder:
            “Önce elle yaz, gerçekten gerekirse framework kullan”

        6️⃣ Imposter vs Stub
            Stub	            Imposter
            -------------       -------------
            Değer döndürür	    Davranışı kaydeder
            State verification	Interaction verification
            Pasif	            Aktif

        7️⃣ TDD’de Imposter kullanma kuralı (Kent Beck tarzı)
            ✔️ Bir yan etkiyi test ediyorsan
            ✔️ Bir mesaj gönderildi mi? sorusu varsa
            ✔️ Gerçek nesne testi zorlaştırıyorsa
            🚫 Sadece değer hesaplıyorsan
            🚫 Kolayca state assertion yapabiliyorsan

        8️⃣ Kent Beck neden “Imposter” der?
            Kelime özellikle seçilmiştir:
            Gerçek gibi davranan ama aslında olmayan
            Bu, testte yaptığımız şeyi etik olarak net anlatır 😄
            “Bu nesne yalan söylüyor ama test için gerekli.”

        🔚 Özet
            Imposter = Gerçek nesne gibi davranan test nesnesi
            Amaç:
                Testi izole etmek
                Etkileşimi doğrulamak
            Kent Beck:
                Basit, elle yazılmış
                Gerektikçe kullanılan
                TDD akışına uygun
 */
public class ImposterTest {
    @Test
    void shouldSendWelcomeEmail() {
        EmailSenderImposter imposter = new EmailSenderImposter();
        UserService service = new UserService(imposter);

        service.register("cemil@example.com");

        assertTrue(imposter.sendCalled);
        assertEquals("cemil@example.com", imposter.sentTo);
    }

}
