# Reconciling Differences - Farklılıkları Uzlaştırmak

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
