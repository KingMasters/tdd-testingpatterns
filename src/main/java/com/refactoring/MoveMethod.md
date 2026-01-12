---

# Kent Beck’e göre **Move Method** nedir?

Kent Beck için **Move Method**:

> **“Bir metodu ait olduğu yere taşıma”**dır.
> Ama bu “daha düzenli olsun” diye yapılan bir taşıma değildir.

Asıl mesele şudur:

> ❝ *Davranış yanlış nesnede duruyor.* ❞

Yani Move Method,
**yanlış sorumluluğu düzeltme refactoring’i**dir.

---

## Beck’in tanımını parça parça okuyalım

### ❝ How do you move a method to the place where it belongs? ❞

➡️ Önce şunu fark ediyorsun:

> “Bu metot, başka bir nesnenin verisini daha iyi tanıyor.”

Bu, Kent Beck’in çok önemsediği bir sinyaldir:

* Bir metot **başka bir nesnenin field’larını** okuyorsa
* O nesneyle daha çok ilgileniyorsa

👉 **Yanlış yerde duruyordur**

---

## Beck’in “How” adımlarını TDD aklıyla açalım

---

## 1️⃣ Copy the method

Önce **davranışı bozmadan** kopyalarsın.

> ❗ TDD refleksi:
> *Önce testler yeşil kalmalı.*

Bu yüzden **taşıma = kopyalama + delegasyon**.

---

## 2️⃣ Paste the method into the target class

Metodu **ait olduğu sınıfa** yapıştırırsın.

### Örnek (yanlış yer)

```java
class Order {
    int quantity;
    Product product;

    int calculatePrice() {
        return quantity * product.getUnitPrice();
    }
}
```

Burada soru:

> “Fiyat bilgisi kimin kavramı?”

➡️ **Product**

---

### Doğru yer (taşıma sonrası)

```java
class Product {
    int unitPrice;

    int priceFor(int quantity) {
        return unitPrice * quantity;
    }
}
```

---

## 3️⃣ Compile it

Kent Beck’in özellikle vurguladığı şey:

> **Her adımda sistem çalışır olmalı**

Bu yüzden:

* İsimleri düzenlersin
* Gerekli parametreleri eklersin

---

## 4️⃣ Original object referenced? → Parameter ekle

Beck’in çok net bir kuralı var:

> ❝ *If variables of the original object are referenced, then pass them as parameters.* ❞

Yani:

* Metot **sadece okuma yapıyorsa** → parameter geç
* Metot **state değiştiriyorsa** → 🚫 dur

### Neden?

Çünkü:

> “Başkasının state’ini değiştiren metot **yanlış tasarımdır**.”

---

### Doğru örnek (okuma var)

```java
class Product {
    int unitPrice;

    int priceFor(int quantity) {
        return unitPrice * quantity;
    }
}
```

---

### Yanlış örnek (state değiştiriyor)

```java
class Product {
    void changeOrderQuantity(Order order) {
        order.quantity = 10; // ❌
    }
}
```

➡️ Beck burada net:

> **Give up.**
> Bu metot taşınmaz, **tasarım problemi vardır**.

---

## 5️⃣ Replace original method with invocation

Orijinal metot artık sadece **delegator** olur:

```java
class Order {
    int quantity;
    Product product;

    int calculatePrice() {
        return product.priceFor(quantity);
    }
}
```

Bu aşama Kent Beck için çok kritik:

* Testler bozulmaz
* Davranış yer değiştirmiştir
* Sistem hâlâ çalışır

---

## Kent Beck açısından Move Method’in anlamı

### 🔹 Extract Method’den farkı

* Extract → **isim vermek**
* Move → **sahibini bulmak**

---

### 🔹 Inline Method ile ilişkisi

Move Method sonrası:

* Eski sınıftaki metot bazen anlamsızlaşır
* Ve **inline edilir**

---

## Move Method = Design Correction

Kent Beck için Move Method şudur:

> ❝ *“Design is correcting mistakes, not predicting the future.”* ❞

Yani:

* Önceden yanlış yere koyduk
* Testler ve kullanım bunu gösterdi
* Şimdi **doğru yere alıyoruz**

---

## Kısa özet (Beck diliyle)

Move Method:

* Kod düzenlemek için değil
* Katman temizlemek için değil

👉 **Yanlış yerde duran davranışı**
👉 **Gerçek sahibine taşımak** içindir.

Ve altın kural:

> **Eğer taşıdığın metot başka nesnenin state’ini değiştiriyorsa, dur.**
> Bu refactoring değil, **tasarım alarmıdır** 🚨

---
