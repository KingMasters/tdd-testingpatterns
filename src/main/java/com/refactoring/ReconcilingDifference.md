# Reconciling Differences - Farklılıkları Uzlaştırmak

Bu refactoring, Beck’in tasarım felsefesinin **çekirdeğidir**.

---

# Kent Beck’e göre **Reconciling Differences**

## Reconciling Differences nedir?

Kent Beck için Reconciling Differences:

> **Benzer olan şeyleri gerçekten aynı hale getirip, fazlalığı silme** işidir.

Ama önemli fark:

* “DRY olsun” diye yapılmaz
* **Davranışı koruyarak** yapılır

Kent Beck’in ünlü cümlesi:

> **“First make the change easy, then make the easy change.”**

Reconciling Differences = *Change’i kolaylaştırmak*

---

# “This refactoring occurs at all levels of scale”

Bu cümle çok kritik.
Beck şunu söyler:

> Aynı düşünce **satırdan mimariye kadar** çalışır.

Şimdi senin verdiğin 4 maddeyi **aynı mantıkla** tek tek açalım.

---

## 1️⃣ Two loop structures are similar

> *By making them identical, you can merge them.*

### Önce (benzer ama farklı)

```java
for (Order o : orders) {
    total += o.price();
}

for (Order o : discountedOrders) {
    total += o.price() * 0.9;
}
```

Burada:

* Döngü yapısı aynı
* İç davranış **neredeyse** aynı

### Reconciling step

Farkı **parametreleştir**:

```java
double sum(List<Order> orders, double multiplier) {
    double total = 0;
    for (Order o : orders) {
        total += o.price() * multiplier;
    }
    return total;
}
```

Artık:

* Döngüler **aynı**
* İki döngü → **bir döngü**

---

## 2️⃣ Two branches of a conditional are similar

> *By making them identical, you can eliminate the conditional.*

### Önce

```java
if (isVip) {
    price = amount * 0.9;
} else {
    price = amount * 1.0;
}
```

### Reconciling

Fark:

* Sadece çarpan

```java
double multiplier = isVip ? 0.9 : 1.0;
price = amount * multiplier;
```

Koşul **ortadan kalktı**.

Kent Beck açısından:

> **Koşul = fark sinyali**

---

## 3️⃣ Two methods are similar

> *By making them identical, you can eliminate one.*

### Önce

```java
void sendEmailNotification() {
    logger.log("email sent");
}

void sendSmsNotification() {
    logger.log("sms sent");
}
```

### Reconciling

Gerçek fark:

* Mesaj içeriği

```java
void sendNotification(String type) {
    logger.log(type + " sent");
}
```

Sonuç:

* 2 metot → 1 metot

---

## 4️⃣ Two classes are similar

> *By making them identical, you can eliminate one.*

### Önce

```java
class FileLogger {
    void log(String msg) { ... }
}

class DbLogger {
    void log(String msg) { ... }
}
```

Burada Beck şunu sorar:

> “Gerçek fark nerede?”

Eğer fark:

* Sadece **konfigürasyon**
* Ya da **küçük davranış**

### Reconciling → Strategy

```java
interface LogDestination {
    void write(String msg);
}
```

Ama eğer fark **yoksa**:
➡️ Sınıflardan biri **silinir**.

---

# Reconciling Differences neden bu kadar temel?

Çünkü bu refactoring:

* Extract Method’a zemin hazırlar
* Inline Method’a gerek bırakır
* Move Method’ı mümkün kılar
* Migrate Data’yı güvenli yapar

Kent Beck’in gözünde:

> **Refactoring = benzerlikleri ortaya çıkarmak**

---

# Reconciling ≠ Abstraction

Önemli fark:

* Önce **aynı yap**
* Sonra **abstraction düşün**

Kent Beck buna özellikle dikkat çeker:

> “Don’t abstract differences. Eliminate them.”

---

# Kısa özet (Kent Beck diliyle)

Reconciling Differences:

* Kod silme refactoring’idir
* Ama **rastgele silme değil**
* **Benzerliği zorla ortaya çıkarma**dır

Özet cümle:

> **“Make things the same, then delete one.”**

---

Kent Beck’in **Reconciling Differences** kavramı, TDD ve yazılım tasarımında sıkça karşılaşılan **zıt gibi görünen ihtiyaçların aynı anda karşılanması** fikrini anlatır.

> **İyi tasarım, bir tarafı seçmek değil,  
> iki tarafı da tatmin eden bir çözüm bulmaktır.**

---

## Reconciling Differences Ne Anlatır?

Yazılım geliştirme sürecinde sürekli karşılaştığımız çelişkiler vardır:

- Basitlik ↔ Esneklik
- Hız ↔ Kalite
- Somut çözüm ↔ Genel çözüm
- Bugünü çözmek ↔ Yarını düşünmek

Reconciling Differences, bu çelişkilerden **birini feda etmek yerine**, ikisini de yaşayabilecek bir yapı kurmayı hedefler.

---

## Kent Beck Bu Kavramla Neyi Vurgular?

Kent Beck’e göre kötü tasarım genellikle şuradan doğar:

- “Şimdiden her şeyi kapsayayım” → aşırı tasarım
- “Şimdilik böyle kalsın” → kırılgan kod

Reconciling Differences şunu söyler:

> **Bugün için en basit çözümü yaz,  
> ama yarın değişebilecek noktaları kilitleme.**

---

## TDD ile İlişkisi

TDD döngüsü:

Red → Green → Refactor

Bu döngü Reconciling Differences’ın doğal sonucudur:

- **Red**: Somut bir ihtiyacı testle ifade et
- **Green**: En basit çözümü yaz
- **Refactor**: Gerektiği kadar genelleştir

Böylece:
- Basitlik korunur
- Esneklik sonradan eklenir

---

## Kötü Yaklaşım: Baştan Genelleştirmek

```java
class StringCalculator {
    int add(String input, String delimiterPattern) {
        // karmaşık ve erken genelleştirilmiş kod
        return 0;
    }
}
```

Sorunlar:
- Okunabilirlik düşer
- Test yazmak zorlaşır
- Değişim maliyeti artar

---

## Reconciling Differences Uygulanan Yaklaşım

### 1. Somut Testle Başla

```java
@Test
void emptyStringReturnsZero() {
    assertEquals(0, calculator.add(""));
}
```

### 2. En Basit Kod

```java
int add(String input) {
    if (input.isEmpty()) return 0;
    return Integer.parseInt(input);
}
```

### 3. Yeni İhtiyaç Gelince Genelleştir

```java
@Test
void commaSeparatedNumbersAreSummed() {
    assertEquals(3, calculator.add("1,2"));
}
```

```java
int add(String input) {
    if (input.isEmpty()) return 0;
    String[] numbers = input.split(",");
    return Arrays.stream(numbers)
                 .mapToInt(Integer::parseInt)
                 .sum();
}
```

Bu yaklaşım:
- Bugünü çözer
- Yarını kapatmaz

---

## Compromise ile Farkı

Reconciling Differences, **compromise (orta yol)** değildir.

- Compromise: İki taraf da eksik kalır
- Reconciling: İki taraf da tam olarak çalışır

Kent Beck için tasarım:
> **Karşıt güçler arasında yaratıcı bir çözümdür.**

---

## Reconciling Differences Nerelerde Görülür?

Bu yaklaşım, Kent Beck’in sık kullandığı birçok teknikle birlikte gelir:

| Teknik | Uzlaştırılan Çelişki |
|------|----------------------|
| TDD | Hız ↔ Kalite |
| Refactoring | Güvenlik ↔ Değişim |
| YAGNI | Basitlik ↔ Gelecek |
| Rule of Three | Somutluk ↔ Genellik |
| Isolate Change | Basitlik ↔ Değişebilirlik |
| Collection Parameter | Okunabilirlik ↔ Esneklik |

---

## Isolate Change ile Bağlantısı

Reconciling Differences şunu sorar:
> “Bu iki ihtiyacı nasıl birlikte yaşatırım?”

Isolate Change ise cevaplardan biridir:
> “Değişecek olanı tek bir yerde topla.”

Bu yüzden iki kavram Kent Beck’te sık sık birlikte geçer.

---

## Kent Beck Özeti

> **“Good design is about reconciling forces,  
> not choosing sides.”**

Ya da sade haliyle:

> **Zıtlıkları seçme, uzlaştır.**
