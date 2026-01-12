Evet — şimdi **daha önce Kent Beck bakışıyla anlattığım “Migrate Data” refactoring’ini**,
**senin verdiğin adımları birebir merkeze alarak**, aynı Extract / Inline / Move çizgisinde **net ve mekanik + zihinsel gerekçesiyle** açıklıyorum.

Bu refactoring özellikle Beck’in şu fikrini çok iyi yansıtır:

> **“Davranışı değil, veriyi adım adım değiştir.”**

---

# Kent Beck’e göre **Migrate Data**

## Migrate Data nedir? (kısa hatırlatma)

Kent Beck için **Migrate Data**:

* Büyük bir “field değişikliği” değildir
* Bir anda yapılan kırıcı dönüşüm değildir
* **Eski ve yeni formatın bir süre birlikte yaşamasıdır**

Amaç:

> **Davranışı bozmadan veri temsilini değiştirmek**

---

# 1️⃣ KLASİK AKIŞ

(Internal data format değişiyor)

Senin verdiğin adımlarla tek tek gidelim.

---

## 1️⃣ Add an instance variable in the new format

İlk refleks:

> **Eskisini silme.**

Yeni veri formatını **yanına ekle**.

### Örnek

```java
class User {
    String birthDate; // eski: "1990-01-01"
}
```

Yeni format:

```java
class User {
    String birthDate;       // eski
    LocalDate birthDateV2;  // yeni
}
```

Kent Beck açısından:

* Risk yok
* Davranış değişmedi
* Testler yeşil

---

## 2️⃣ Set the new format variable everywhere you set the old format

Ne zaman eskiyi set ediyorsan:

* **Yeni formatı da set et**

```java
void setBirthDate(String date) {
    this.birthDate = date;
    this.birthDateV2 = LocalDate.parse(date);
}
```

Bu adımın amacı:

> **Verinin iki temsili de senkron kalsın**

Henüz hiçbir kullanım değişmedi.

---

## 3️⃣ Use the new format variable everywhere you use the old format

Şimdi **okuma tarafı** taşınır.

Önce:

```java
int age() {
    return Period.between(
        LocalDate.parse(birthDate),
        LocalDate.now()
    ).getYears();
}
```

Sonra:

```java
int age() {
    return Period.between(
        birthDateV2,
        LocalDate.now()
    ).getYears();
}
```

Kent Beck için burası çok önemli:

> **Davranış aynı, veri temsili farklı**

---

## 4️⃣ Delete the old format

Artık:

* Hiçbir yerde okunmuyor
* Sadece yazılıyorsa

➡️ **Sil**

```java
class User {
    LocalDate birthDate;
}
```

Bu noktada:

* Kod sadeleşir
* Teknik borç kapanır

---

## 5️⃣ Change the external interface to reflect the new format

En sona bırakılır çünkü:

* Burası **kırıcı değişikliktir**
* En riskli adımdır

```java
void setBirthDate(LocalDate date) {
    this.birthDate = date;
}
```

Kent Beck’in yaklaşımı:

> **Önce içeride güvene al, dışarıyı en son değiştir**

---

# 2️⃣ ALTERNATİF AKIŞ

(API önce değişmeliysa)

Beck özellikle şunu ekler:

> “Sometimes, however, you will want to change the API first.”

Bu genelde:

* Public API
* Kütüphane
* Çok kullanılan servislerde olur

---

## 1️⃣ Add a parameter in the new format

```java
void setBirthDate(LocalDate date) {
    this.birthDate = date.toString(); // şimdilik
}
```

Yeni API dışarıya açıldı
Ama içeride hâlâ eski format var.

---

## 2️⃣ Translate from the new format to the old internal representation

```java
void setBirthDate(LocalDate date) {
    this.birthDate = date.toString(); // internal hâlâ eski
}
```

Bu adım:

> **Uyumluluk katmanı**dır

---

## 3️⃣ Delete the old format parameter

```java
// setBirthDate(String date) ❌ silinir
```

Artık dışarıdan:

* Sadece yeni format gelir

---

## 4️⃣ Replace uses of the old format with the new format

Şimdi içeri geçilir:

* Okumalar
* Hesaplamalar
* Karşılaştırmalar

Yeni formatla yapılır.

---

## 5️⃣ Delete the old format

En son:

* Field
* Converter
* Geçici kodlar

➡️ **Tam temizlik**

---

# Kent Beck neden bu kadar adımlı anlatıyor?

Çünkü Migrate Data’da en büyük risk şudur:

> **Sessiz davranış değişikliği**

Bu adımlar:

* Her aşamada sistem çalışır
* Testler yeşildir
* Geri dönüş mümkündür

---

# Migrate Data ↔ Diğer refactoring’ler

Çok sık şu zincir oluşur:

* Extract Method → veriye dokunmak için
* Move Method → davranışı doğru yere almak için
* **Migrate Data → modeli düzeltmek için**
* Inline → geçici kodları silmek için

---

# Kısa özet (Kent Beck diliyle)

Migrate Data:

* “Field değiştirme” değildir
* “Büyük refactor” değildir

👉 **Eskiyle yeniyi bir süre birlikte yaşat**
👉 **Davranışı önce koru**
👉 **Temizliği en sona bırak**

Altın cümle:

> **“Change representation without changing behavior.”**

---

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
