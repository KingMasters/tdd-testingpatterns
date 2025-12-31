# Kent Beck – Isolate Change

Kent Beck’in **Isolate Change** kavramı, TDD (Test-Driven Development) ve yazılım tasarımının en temel ilkelerinden biridir.

> **Değişecek olan şeyi, değişmeyenden ayır.  
> Değişim bir yere sıkışsın, her yere yayılmasın.**

---

## Isolate Change Ne Anlatır?

Isolate Change, yazılımda **değişmesi muhtemel olan kuralların ve davranışların**, sistemin geri kalanından ayrılması gerektiğini söyler.

Bir şey değişecekse:
- Tek bir yerde değişmeli
- Minimum sayıda sınıfı etkilemeli
- Minimum sayıda testi kırmalıdır

Kent Beck’e göre yazılım geliştirmede en pahalı şey:
> **Kod yazmak değil, değişiklik yapmaktır.**

---

## TDD ile İlişkisi

TDD döngüsü:

Red → Green → Refactor

Bu döngünün sürdürülebilir olması için:
- Değişikliklerin lokal olması gerekir
- Testler zincirleme şekilde kırılmamalıdır

Isolate Change yoksa:
- Refactor pahalıdır
- Geliştirici değişiklik yapmaktan korkar
- TDD yavaşlar ve acı verir

---

## Isolate Change Olmayan Kötü Bir Örnek

```java
class OrderService {

    double calculateTotal(Order order) {
        double total = 0;
        for (Item item : order.items()) {
            if (item.type() == ItemType.DISCOUNTED) {
                total += item.price() * 0.9;
            } else {
                total += item.price();
            }
        }
        return total;
    }
}
```

Bu yaklaşımın sorunları:
- İndirim kuralı değişirse bu sınıf değişir
- Aynı hesaplama başka yerlerde varsa hepsi etkilenir
- Testler zincirleme kırılır

---

## Isolate Change Uygulanmış Tasarım

```java
interface PricingPolicy {
    double priceFor(Item item);
}
```

```java
class DefaultPricingPolicy implements PricingPolicy {

    @Override
    public double priceFor(Item item) {
        if (item.type() == ItemType.DISCOUNTED) {
            return item.price() * 0.9;
        }
        return item.price();
    }
}
```

```java
class OrderService {

    private final PricingPolicy pricingPolicy;

    OrderService(PricingPolicy pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }

    double calculateTotal(Order order) {
        return order.items()
                    .stream()
                    .mapToDouble(pricingPolicy::priceFor)
                    .sum();
    }
}
```

---

## Test Tarafındaki Etkisi

```java
@Test
void usesPricingPolicy() {
    PricingPolicy fakePolicy = item -> 100;

    OrderService service = new OrderService(fakePolicy);

    assertEquals(100, service.calculateTotal(orderWithOneItem()));
}
```

---

## Isolate Change İçin Kullanılan Teknikler

| Teknik | İzole Edilen Değişim |
|------|----------------------|
| Interface | Değişen davranış |
| Strategy Pattern | Algoritma |
| Factory Method | Nesne yaratma |
| Null Object | Koşullu mantık |
| Pluggable Object | Tak-çıkar davranış |
| Collection Parameter | Parametre sayısı |

---

## Kent Beck Özeti

> **Change happens.  
> The trick is to decide where.**
