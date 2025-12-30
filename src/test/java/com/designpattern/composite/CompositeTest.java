package com.designpattern.composite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Composite = Tekil nesne ile nesne grubunu aynı arayüzden kullanabilme
    1️⃣ Problemi netleştirelim
        Şöyle bir ihtiyaç düşün:
        Tek bir nesne var → File
        Bir de bu nesnelerin grubu var → Directory
        İkisine de aynı şekilde davranmak istiyorsun

        ❌ Composite yoksa:
        if (item instanceof File) {
            item.size();
        } else if (item instanceof Directory) {
            item.totalSize();
        }

        ➡️ if-else patlaması
        ➡️ OCP ihlali
        ➡️ Test yazmak zor

        Composite Varsa:
            Fake / Mock yok
            Gerçek davranış
            Kolay assertion
            Yeni tür eklemek kolay, Testler değişmez

       5️⃣ Kent Beck Composite’i nasıl görür?
            Kent Beck’e göre Composite:
                Önceden planlanan bir desen değildir
                Şu anda oluşur:
                “Bir nesneyle, bir grup nesneye aynı mesajı atıyorum”

            O noktada:
                if-else görürsün
                Duplicate kod artar
                Testler çirkinleşir
                ➡️ Refactor → Composite

        7️⃣ Composite’in tehlikeleri
            Kent Beck uyarır:
            ⚠️ Her şeyi Composite yapma
            ⚠️ Gereksiz soyutlama yaratma

            Kötü sinyaller:
                Tek bir Leaf var, grup hiç yok
                Henüz hiç if-else yok
                Testler zorlaşmıyor
                ➡️ Composite erken olur
 */
public class CompositeTest {
    @Test
    void directorySizeIsSumOfItems() {
        Directory dir = new Directory();
        dir.add(new FileItem(10));
        dir.add(new FileItem(20));

        assertEquals(30, dir.size());
    }

}
