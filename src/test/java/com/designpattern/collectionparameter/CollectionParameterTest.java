package com.designpattern.collectionparameter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Collection Parameter kullanmasının nedeni; davranışı genişletilebilir, testleri sade ve tasarımı esnek tutmaktır.
    Bu, Implementation’a değil, davranışa odaklanma ilkesinin çok tipik bir örneğidir.

    1️⃣ Collection Parameter nedir?
    Bir metoda;
        String a, String b, String c
            gibi tek tek parametreler vermek yerine:

        List<String> values
            gibi bir koleksiyon vermektir.

        Problemler:
            4. sayı gelirse → metod değişir
            Testler bozulur
            Overload cehennemi başlar
            Davranış değil adet test edilir
            Kent Beck bunu tasarım kokusu olarak görür.

    2️⃣ Kent Beck neden Collection Parameter kullanır?
        🎯 1. Değişime dayanıklı tasarım
        TDD’de test yazarken şu soruyu sorar:
        “Yarın bu davranış 1 değil 10 şeyle çalışırsa ne olacak?”

        Collection Parameter ile:
            Parametre sayısı artınca metod imzası değişmez
            Testler bozulmaz

     🎯 3. Primitive Obsession’dan kaçınır
        Birden fazla primitive → genelde yanlış soyutlama.
        Kent Beck:
        “Birden fazlaysa, muhtemelen bir koleksiyon ya da nesnedir.”

     🎯 4. Polymorphism & Plugability
            Collection verildiğinde:
            ArrayList
            LinkedList
            Empty list
            Fake / Test list
            kolayca değiştirilebilir → Pluggable Object etkisi.

      5️⃣ Kent Beck’in TDD felsefesiyle bağlantı
            Collection Parameter genellikle şu patternlerle birlikte görülür:
            Kavram	            İlişki
            ----------------    --------------------------------
            Pluggable Object	Farklı collection’lar takılabilir
            Value Object	    Koleksiyon çoğu zaman bir VO’ya dönüşür
            Command	            Liste = yapılacak işler
            Composite	        Liste içindeki öğeler aynı arayüzde

       6️⃣ Ne zaman özellikle tercih etmeli?
            ✔️ Eğer:
            Parametre sayısı değişebilir
            “Birden fazla şey” kavramı varsa
            Testleri sade tutmak istiyorsan
            Geleceği tahmin etmek istemiyorsan
            ➡️ Collection Parameter kullan

       7️⃣ Kent Beck özeti (tek cümle)
            “Bugünkü ihtiyacı karşıla, yarın değişebilecek noktaları kilitleme.”
            Collection Parameter bunun TDD’deki en sade araçlarından biridir.

 */
public class CollectionParameterTest {
    @Test
    void sumOfNumbers() {
        Calculator calculator = new Calculator();

        int result = calculator.sum(List.of(1, 2, 3));

        assertEquals(6, result);
    }

    @Test
    void sumOfEmptyListIsZero() {
        Calculator calculator = new Calculator();

        int result = calculator.sum(List.of());

        assertEquals(0, result);
    }


}
