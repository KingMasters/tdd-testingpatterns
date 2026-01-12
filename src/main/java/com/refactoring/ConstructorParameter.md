Bu refactoring, Kent Beck’in şu temel fikrini çok net gösterir:

> **“Bir bilgi sürekli metoda giriyorsa, o artık nesnenin state’idir.”**

---

# Kent Beck’e göre

## **Method Parameter → Constructor Parameter**

### Problem nedir?

Aşağıdaki sinyalleri görüyorsan:

* Aynı parametre **birçok metotta tekrar tekrar** geçiliyor
* Metotlar o parametre olmadan **anlamsız**
* Parametre metot çağrısının değil, **nesnenin kimliğinin parçası**

Kent Beck’in yorumu:

> **“Bu bilgi metot için değil, nesne için.”**

---

# Tanımı açalım

> **How do you move a parameter from a method or methods to the constructor?**

Amaç:

* Bilgiyi geçici olmaktan çıkar
* **Nesnenin kalıcı durumu (state)** yap

Bu, tasarımda **“stateless → stateful”** geçiştir.

---

# Şimdi verdiğin **HOW** adımlarını tek tek açalım

Aşağıdaki örnek üzerinden gidelim.

---

## Başlangıç durumu

```java
class InvoiceService {

    int calculateTotal(Invoice invoice, int taxRate) {
        return invoice.amount() + invoice.amount() * taxRate / 100;
    }
}
```

### Sinyal:

* `taxRate` her çağrıda geçiliyor
* Servis onsuz çalışamaz

---

## 1️⃣ Add a parameter to the constructor

```java
class InvoiceService {

    InvoiceService(int taxRate) {
    }

    int calculateTotal(Invoice invoice, int taxRate) {
        return invoice.amount() + invoice.amount() * taxRate / 100;
    }
}
```

Bu adımda:

* Henüz davranış değişmedi
* Sadece yapı hazırlandı

---

## 2️⃣ Add an instance variable with the same name as the parameter

```java
class InvoiceService {

    private int taxRate;

    InvoiceService(int taxRate) {
    }

    int calculateTotal(Invoice invoice, int taxRate) {
        return invoice.amount() + invoice.amount() * taxRate / 100;
    }
}
```

Kent Beck burada bilerek **aynı ismi** önerir.

---

## 3️⃣ Set the variable in the constructor

```java
InvoiceService(int taxRate) {
    this.taxRate = taxRate;
}
```

Artık:

* Constructor parametresi → state

---

## 4️⃣ One by one, convert references to “parameter” to “this.parameter”

Metot içi kullanım:

```java
int calculateTotal(Invoice invoice, int taxRate) {
    return invoice.amount() + invoice.amount() * this.taxRate / 100;
}
```

Burada **iki `taxRate` bir arada** bulunur:

* biri parametre
* biri instance variable

Bu **bilinçli bir geçiş durumudur**.

---

## 5️⃣ When no more references exist to the parameter, delete the parameter from the method and all caller

Artık metot parametresi gereksiz:

```java
int calculateTotal(Invoice invoice) {
    return invoice.amount() + invoice.amount() * this.taxRate / 100;
}
```

Ve çağrılar:

```java
service.calculateTotal(invoice);
```

Kent Beck’in kuralı:

> **“Derleyici seni doğru yere götürür.”**

---

## 6️⃣ Remove the now-superfluous “this.” from references

Eğer gölge yoksa:

```java
return invoice.amount() + invoice.amount() * taxRate / 100;
```

Bu tamamen **okunabilirlik** adımıdır.

---

## 7️⃣ Rename the variable correctly

Bazen son isim:

* `taxRate` → `defaultTaxRate`
* `taxRate` → `vatRate`

Bu adım:

* Tasarım niyetini netleştirir

---

# Kent Beck neden bu refactoring’i önerir?

### 1️⃣ Parametre = geçici bilgi

### 2️⃣ Field = kalıcı bilgi

Kent Beck’in zihinsel modeli:

| Tür                   | Anlam             |
| --------------------- | ----------------- |
| Method Parameter      | Anlık bağlam      |
| Constructor Parameter | Nesne kimliği     |
| Instance Variable     | Davranışın temeli |

---

### 3️⃣ Method Object’e doğal geçiş

Bu refactoring çoğu zaman:

* Method Object sonrası
* Strategy / Service tasarımına geçerken çıkar

---

# Ne zaman YAPILMAMALI?

### ❌ Parametre gerçekten çağrıya özgüyse

### ❌ Nesne farklı değerlerle tekrar tekrar kullanılacaksa

### ❌ State taşımak concurrency problemi yaratacaksa

Kent Beck:

> **“State pahalıdır, ama doğru yerde olursa.”**

---

# Kısa özet (Kent Beck diliyle)

**Method Parameter → Constructor Parameter**:

* Bilgiyi taşımaktan vazgeç
* Sahibini bul

Altın cümle:

> **“If a parameter never changes, it doesn’t belong to the method.”**

---
