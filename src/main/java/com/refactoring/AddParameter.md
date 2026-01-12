
> **“Değişikliği küçük ve güvenli adımlarla yap.”**

---

# Kent Beck’e göre **Add Parameter**

## Add Parameter nedir?

Kent Beck için **Add Parameter**:

* “Metoda bir parametre eklemek” değildir sadece
* **Yeni bir ihtiyacı, mevcut davranışı bozmadan** sisteme sokma yoludur

Genelde şu durumda çıkar:

* Yeni bir kural / bilgi lazım oldu
* Ama tasarımı henüz parçalamak istemiyorsun

---

## Tanımı okuyalım

> **How do you add a parameter to a method?**

Kent Beck burada şunu ima eder:

> “Bu küçük görünen değişiklik, kontrolsüz yapılırsa zincirleme kırılmalara yol açar.”

Bu yüzden **mekanik ve güvenli bir yol** tarif eder.

---

# Şimdi senin verdiğin **HOW** adımlarını tek tek açalım

---

## 1️⃣ If the method is in an interface, add the parameter to the interface first

Bu madde **kritik**tir.

### Neden?

Çünkü:

* Interface = **contract**
* Önce contract değişmeden implementasyon değiştirilmez

### Örnek

```java
interface DiscountPolicy {
    int discount(int amount);
}
```

Yeni ihtiyaç:

> VIP müşteriler için farklı davranış

Önce interface:

```java
interface DiscountPolicy {
    int discount(int amount, boolean vip);
}
```

Kent Beck açısından:

> **Derleme hatası = yol haritası**

---

## 2️⃣ Add the parameter

Şimdi implementasyonlar güncellenir:

```java
class DefaultDiscountPolicy implements DiscountPolicy {
    public int discount(int amount, boolean vip) {
        return vip ? amount / 10 : 0;
    }
}
```

Bu aşamada:

* Kod kırılır (bilerek)
* Ama **kontrollü kırılır**

---

## 3️⃣ Use the compiler errors to tell you what calling code you need to change

Bu, Kent Beck’in en sevdiği refactoring silahıdır.

> **“Let the compiler guide you.”**

### Önce

```java
policy.discount(100);
```

Derleme hatası:

```
method discount in interface DiscountPolicy cannot be applied to given types
```

### Sonra

```java
policy.discount(100, customer.isVip());
```

Bu şekilde:

* Hiçbir çağrıyı unutmazsın
* Manuel aramaya gerek kalmaz
* Davranış bilinçli şekilde güncellenir

---

# Kent Beck neden Add Parameter’ı sever?

### 1️⃣ Davranışı genişletir ama bozmaz

* Var olan kod çalışıyordu
* Yeni bilgi eklenir
* Eski davranış **kontrollü** evrilir

---

### 2️⃣ Tasarımı erken zorlamaz

Add Parameter genelde:

* Geçici çözümdür
* Bir süre sonra şuna evrilir:

    * Method Object
    * Value Object
    * Strategy

Kent Beck’in bakışı:

> **“Önce çalıştır, sonra tasarımı büyüt.”**

---

### 3️⃣ Değişim eksenini görünür kılar

Yeni parametre şu soruyu sordurur:

> “Bu bilgi gerçekten bu metoda mı ait?”

Çoğu zaman cevap:

* Hayır → Move Method
* Hayır → Method Object
* Hayır → Extract Class

---

# Add Parameter ne zaman YAPILMAMALI?

Kent Beck burada da nettir:

### ❌ Parametre sayısı şişmeye başladıysa

➡️ Method Object sinyali

### ❌ Parametre farklı kavramları temsil ediyorsa

➡️ Value Object sinyali

### ❌ Aynı parametre birçok metoda ekleniyorsa

➡️ Tasarım alarmı 🚨

---

# Add Parameter ↔ diğer refactoring’ler

Çok sık görülen zincir:

* Add Parameter
* Add Parameter
* Add Parameter
* 😬
* **Method Object / Extract Class**

Kent Beck buna bilerek izin verir:

> “Önce ihtiyacı görünür kıl.”

---

# Kısa özet (Kent Beck diliyle)

Add Parameter:

* Basit bir mekanik değişikliktir
* Ama **tasarım sinyali taşır**

Altın cümle:

> **“If you keep adding parameters, you’re discovering an object.”**

---
