package com.testingpatterns.fixture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
Fixture : Testlerin çalışması için gereken ortak başlangıç durumudur.
          Testten önce sistemi bilinen ve tekrarlanabilir bir duruma getiren her şey.

Fixture neyi kapsar?
            Bir testin çalışabilmesi için gereken:
            Nesnelerin oluşturulması
            Başlangıç değerlerinin ayarlanması
            Test verileri
            Gerekirse sahte (stub/mock) bağımlılıklar
            Yani testin Given kısmı diyebiliriz.

@BeforeEach
        ➡ fixture kurulum yeridir

        Fixture mümkün olduğunca küçük olmalı
        Gereksiz ortak kurulumdan kaçınılmalı
        Testte kullanılmayan şey fixture’a girmemeli
 */
public class FixtureTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("cemil");
    }

    @Test
    void FixtureTest(){
        // user nesnesi her testten önce setUp metodu ile oluşturulur
        // Testler burada user nesnesini kullanabilir
    }
}