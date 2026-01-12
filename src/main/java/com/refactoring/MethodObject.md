
# Kent Beck’e göre **Method Object**

## Method Object nedir?

Kent Beck Method Object’i şöyle konumlandırır:

> **“Bir metot, taşıyamayacağı kadar çok sorumluluk ve değişken biriktirdiğinde,
> onu bir nesneye dönüştür.”**

Yani:

* Çok fazla **parametre**
* Çok fazla **local variable**
* Çok fazla **adım / branch / hesap**

varsa, problem **metot seviyesini aşmıştır**.

Çözüm:

> **Metodu nesne yap.**

---

# Tanımı parça parça okuyalım

### ❝ How do you represent a complicated method…? ❞

Kent Beck’in işaret ettiği problem:

* Metot çok karmaşık
* Extract Method denedin ama:

    * parametre sayısı patladı
    * local variable’lar birbirine girdi

Beck’in teşhisi:

> **“Artık bu bir metot değil, gizli bir nesne.”**

---

# Şimdi senin verdiğin **HOW** adımlarını tek tek açalım

---

## 1️⃣ Create an object with the same parameters as the method

Önce:

```java
int calculatePrice(Order order, Customer customer, int quantity, boolean vip) {
    ...
}
```

Method Object sonrası:

```java
class PriceCalculator {

    private final Order order;
    private final Customer customer;
    private final int quantity;
    private final boolean vip;

    PriceCalculator(Order order, Customer customer, int quantity, boolean vip) {
        this.order = order;
        this.customer = customer;
        this.quantity = quantity;
        this.vip = vip;
    }
}
```

Kent Beck için:

> **Parametreler artık state oldu**

---

## 2️⃣ Make the local variables also instance variables of the object

Önce (metot içi karmaşa):

```java
int basePrice = order.price() * quantity;
int discount = 0;
int tax = 0;
```

Sonra (nesne state’i):

```java
class PriceCalculator {

    int basePrice;
    int discount;
    int tax;
}
```

Bu adım çok kritiktir çünkü:

* Artık Extract Method yapmak **kolaylaşır**
* Metot içi bağımlılıklar **görünür olur**

---

## 3️⃣ Create one method called `run()`

```java
class PriceCalculator {

    int run() {
        basePrice = order.price() * quantity;

        if (vip) {
            discount = basePrice / 10;
        }

        tax = (basePrice - discount) / 5;

        return basePrice - discount + tax;
    }
}
```

Kent Beck burada özellikle şunu ister:

> **Davranış birebir aynı kalmalı**

Bu hâlâ refactoring’tir, redesign değildir.

---

## 4️⃣ In the original method, create a new object and invoke `run()`

```java
int calculatePrice(Order order, Customer customer, int quantity, boolean vip) {
    return new PriceCalculator(order, customer, quantity, vip).run();
}
```

Bu noktada:

* Eski metot **delegator**
* Yeni nesne **gerçek davranış sahibi**

---

# Kent Beck neden Method Object önerir?

### 1️⃣ Extract Method tıkandığında

* Çok fazla parametre geçiyorsan
* Metotlar arası state taşıyorsan

➡️ **Method Object**

---

### 2️⃣ Tasarım bir üst seviyeye çıkmak istediğinde

Method Object çoğu zaman şuna evrilir:

* Strategy
* Command
* Domain Object

Beck’in ünlü yaklaşımı:

> **“Today a method, tomorrow an object.”**

---

### 3️⃣ Test yazmak kolaylaşır

Artık:

```java
new PriceCalculator(...).run();
```

tek başına test edilebilir.

---

# Method Object ↔ diğer refactoring’ler

Method Object genelde şu zincirde çıkar:

* Extract Method → yetmedi
* Inline → karmaşa geri geldi
* **Method Object**
* Move Method / Extract Class

Yani:

> **Method Object = geçiş refactoring’i**

---

# Kent Beck’in önemli uyarısı

### ❗ Method Object bir hedef değil

Eğer:

* Nesne sadece `run()` içeriyorsa
* Başka davranış kazanmıyorsa

➡️ Muhtemelen **geçicidir**

Ama eğer:

* Yeni metotlar ekleniyorsa
* Kendi kuralları oluşuyorsa

➡️ **Gerçek domain nesnesi doğuyordur**

---

# Kısa özet (Kent Beck diliyle)

Method Object:

* Metodu “temizlemek” için değil
* Karmaşayı **nesne seviyesine yükseltmek** için vardır

Altın cümle:

> **“When a method starts to look like an object, make it one.”**

---
