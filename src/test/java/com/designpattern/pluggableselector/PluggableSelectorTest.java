package com.designpattern.pluggableselector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Pluggable Selector Pattern
        Hangi davranışın seçileceği kararını, if/switch yerine dışarıdan “takılabilir” bir seçici nesneye vermektir.
        Yani:
        Davranış aynı
        Ama hangi davranışın çalışacağı değişiyor
        👉 Seçim mantığı plug edilir.

    2️⃣ Pluggable Object ile farkı (çok kritik)
                        Pluggable Object	    Pluggable Selector
                        ---------------------   ----------------------
        Değişen şey	    Davranış	            Davranışı seçme kuralı
        if/switch	    Yok	                    Yok
        Ne plug edilir	Algoritma	            Karar mekanizması
        Sorumluluk	    “Nasıl yapılır?”        “Hangisi yapılır?”

        Selector = karar verici

     3️⃣ Ne zaman ortaya çıkar? (Kent Beck sinyalleri)
        Pluggable Selector genelde şurada çıkar:
        if (type == A) use X else use Y
        Aynı işlemi yapan birden fazla implementasyon
        Seçim kuralı zamanla değişebilir
        Testlerde “şu durumda bunu seçmeli” diyorsan

     8️⃣ Kent Beck TDD bakışı
            Kent Beck şunu ister:
            “Seçim mantığını davranıştan ayır.
            Çünkü değişen şey çoğu zaman seçimdir, algoritma değil.”
            Bu yüzden:
            Pluggable Selector = refactor sonucu
            Başta yazılmaz
            if büyümeye başladığında çıkarılır

         9️⃣ Pluggable Selector → Factory’ye evrilir mi?
            Evet, Kent Beck önce Selector, gerekirse sonra Factory der.

         🔟 Özet
            Pluggable Selector nedir?
                → Hangi nesnenin kullanılacağını seçen plug edilebilir karar mekanizması
            Neden?
                → if/switch’i izole etmek, testleri sadeleştirmek
            Ne zaman?
                → Seçim kuralları artmaya başladığında
            TDD’de yeri
                → Refactor aşaması
 */
public class PluggableSelectorTest {
    /*  👉 Burada:
        Seçim kuralını test etmiyoruz
        Sadece “service selector’a güvenir mi?” onu test ediyoruz
    */
    @Test
    void usesSelectedPaymentMethod() {
        PaymentMethod fakeMethod = amount -> amount + 100;

        PaymentSelector fakeSelector = type -> fakeMethod;

        PaymentService service = new PaymentService(fakeSelector);

        assertEquals(200, service.pay("ANY", 100));
    }

    //Selector Testi
    @Test
    void selectsCreditCardForCreditType() {
        PaymentSelector selector = new DefaultPaymentSelector();

        PaymentMethod method = selector.select("CREDIT");

        assertTrue(method instanceof CreditCardPayment);
    }

}
