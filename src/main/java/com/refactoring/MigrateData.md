# Kent Beck – Migrate Data

Kent Beck’in **Migrate Data** kavramı, yazılım tasarımında ve özellikle **refactoring** sürecinde,  
**veri yapılarındaki değişikliklerin güvenli, adım adım ve testler eşliğinde yapılmasını** anlatır.

> **Kod değişir, ama asıl zor olan veriyi değiştirmektir.  
> Migrate Data, bu değişimi kontrollü yapma sanatıdır.**

---

## Migrate Data Nedir?

Migrate Data, bir sistemde:

- Veri modeli
- Alan isimleri
- Veri formatları
- Kalıcılık (persistence) yapıları

değiştirileceği zaman, **eski ve yeni yapının bir süre birlikte yaşamasını** sağlayarak ilerlemeyi önerir.

Amaç:
- Sistemi kırmadan
- Büyük bang (her şeyi bir anda) yapmadan
- Test güvenliğini kaybetmeden

ilerlemektir.

---

## Kent Beck Neden Bu Kavramı Önemser?

Kent Beck’e göre refactoring sadece kodla ilgili değildir.

> **En pahalı refactoring, veri değişimi olandır.**

Çünkü:
- Veri geri döndürülemez olabilir
- Canlı sistemlerde çalışır
- Hata maliyeti çok yüksektir

Migrate Data bu riski **küçük adımlara böler**.

---

## Temel Prensip

Migrate Data şu prensiple çalışır:

> **Önce yeni yapıyı ekle,  
> sonra yavaş yavaş eskiyi terk et.**

Yani:
1. Yeni veri yapısı eklenir
2. Kod her iki yapıyı da destekler
3. Veri kademeli olarak taşınır
4. Eski yapı silinir

---

## Kötü Yaklaşım (Big Bang Migration)

```java
class User {
    String fullName;
}
```

Bir anda şuna geçmek:

```java
class User {
    String firstName;
    String lastName;
}
```

Sorunlar:
- Eski veriler bozulur
- Tüm kod aynı anda değişmelidir
- Geri dönüş neredeyse imkansızdır

---

## Kent Beck Tarzı: Migrate Data Adımları

### 1. Yeni Yapıyı Ekle (Eskiyi Kırmadan)

```java
class User {
    String fullName;
    String firstName;
    String lastName;
}
```

Henüz hiçbir şey silinmez.

---

### 2. İki Yapıyı da Destekle

```java
class User {

    String fullName;
    String firstName;
    String lastName;

    String getFirstName() {
        if (firstName != null) {
            return firstName;
        }
        return fullName.split(" ")[0];
    }
}
```

Sistem çalışmaya devam eder.

---

### 3. Yeni Yapıya Yaz, Eskiden Oku

Yeni kayıtlar artık yeni alana yazılır:

```java
void setName(String first, String last) {
    this.firstName = first;
    this.lastName = last;
}
```

Eski kayıtlar hâlâ okunabilir.

---

### 4. Veriyi Kademeli Taşı

- Background job
- Script
- Manuel batch

ile eski veriler yeni yapıya taşınır.

Bu aşamada:
- Testler yeşildir
- Sistem ayaktadır

---

### 5. Eski Yapıyı Sil

```java
class User {
    String firstName;
    String lastName;
}
```

Artık:
- Kod sade
- Veri modeli net
- Risk minimize edilmiştir

---

## TDD ile İlişkisi

TDD, Migrate Data’nın güvenlik ağıdır.

- Her adımda testler çalışır
- Veri okuma/yazma davranışı test edilir
- Refactor korkusuz yapılır

Kent Beck’in yaklaşımı:
> **Test yoksa migration yok.**

---

## Isolate Change ile Bağlantısı

Migrate Data, değişimi izole etmenin veri tarafındaki karşılığıdır.

- Değişen veri yapısı tek bir yerde yönetilir
- Kodun geri kalanı etkilenmez
- Migration kontrol altındadır

---

## Reconciling Differences ile Bağlantısı

Migrate Data şu iki zıt ihtiyacı uzlaştırır:

- Sistemi çalışır tutmak
- Veri modelini geliştirmek

Yani:
> **Stabilite ↔ Evrim**

---

## Ne Zaman Migrate Data Kullanılır?

- Alan adı değişiyorsa
- Veri parçalanıyorsa veya birleşiyorsa
- Format değişiyorsa (String → Value Object)
- Legacy veri modeli evrim geçiriyorsa

---

## Kent Beck Özeti

> **“Make the change easy,  
> then make the easy change.”**

Migrate Data:
> **Zor veri değişikliklerini, kolay adımlara bölmektir.**
