Evet, şimdi **aynı anlatım çizgisini bozmadan**, birkaç soru önce yaptığım **Kent Beck’e göre Extract Interface** açıklamasını **senin verdiğin “How” adımlarını da içine alarak** baştan ve **tam** olarak anlatıyorum.

Aşağıda anlatım **bilerek mekanik + zihinsel gerekçeyi birlikte** içeriyor; çünkü Kent Beck tam olarak böyle düşünür.

---

# Kent Beck’e göre **Extract Interface**

## Extract Interface nedir? (Hatırlatma)

Kent Beck için **Extract Interface**:

* Baştan yapılan bir mimari karar **değildir**
* “Clean olsun” diye yapılan bir refactoring **değildir**
* **Testlerin ve değişimin zorladığı** bir adımdır

Amaç:

> **Davranışın sahibini değil, değişim eksenini soyutlamak**

---

# Şimdi Beck’in verdiğin **HOW** adımlarını tek tek açalım

---

## 1️⃣ Declare an interface

> *Sometimes the name of the existing class should be the name of the interface…*

### Ne demek istiyor?

Çoğu zaman şunu fark edersin:

* Mevcut sınıfın adı **davranışı temsil ediyor**
* Ama artık o davranışın **birden fazla implementasyonu** var

### Örnek

```java
class PaymentGateway {
    void pay(int amount) { ... }
}
```

Zamanla:

* FakePaymentGateway (test)
* RealPaymentGateway (prod)

geliyor.

### Beck’in dediği:

> O zaman “PaymentGateway” **interface olmalıydı**

### Yapılan şey:

```java
interface PaymentGateway {
    void pay(int amount);
}

class RealPaymentGateway implements PaymentGateway {
    public void pay(int amount) { ... }
}
```

Eğer mevcut sınıfın adı interface’e daha uygunsa:

* Önce sınıfı yeniden adlandır
* Sonra interface’i gerçek ismiyle çıkar

➡️ Bu adım **kavramsal temizliktir**, teknik değil.

---

## 2️⃣ Have the existing class implement the interface

Bu adımın önemi:

* **Davranış değişmez**
* Sadece **tip ilişkisi** eklenir

```java
class RealPaymentGateway implements PaymentGateway {
    public void pay(int amount) { ... }
}
```

Kent Beck burada şunu ister:

> “Bu adımda testlerin hiçbiri bozulmamalı”

Çünkü:

* Henüz hiçbir kullanım değişmedi
* Sadece **kapasite ekledik**

---

## 3️⃣ Add the necessary methods to the interface

> *…expanding the visibility of the methods in the class if necessary.*

### Kritik Kent Beck noktası 👇

Interface’e giren metotlar:

* **Gerçekten kullanılan**
* **Davranışı temsil eden** metotlardır

❌ Yanlış:

```java
interface PaymentGateway {
    void init();
    void connect();
    void close();
}
```

✔ Doğru:

```java
interface PaymentGateway {
    void pay(int amount);
}
```

### Visibility neden artar?

Çünkü:

* Önceden `package-private` / `protected`
* Artık **interface contract’ının parçası**

```java
public void pay(int amount) { ... }
```

Beck için bu bir sinyaldir:

> “Bu davranış artık **başkaları tarafından kullanılmalı**”

---

## 4️⃣ Change type declarations from the class to the interface where possible

Bu adım **Extract Interface’in asıl kazancı**dır.

### Önce (sıkı bağlı)

```java
class PaymentService {
    private RealPaymentGateway gateway;
}
```

### Sonra (gevşek bağlı)

```java
class PaymentService {
    private PaymentGateway gateway;
}
```

Artık:

* Fake takılabilir
* Mock takılabilir
* Yeni implementasyon eklenebilir

Kent Beck açısından:

> **Refactoring burada değer üretir**

Yoksa interface çıkarmanın **hiçbir anlamı yoktur**.

---

# Kent Beck’in bakış açısından kritik uyarılar

### ❗ Interface çıkardın ama kimse kullanmıyor mu?

→ **Yanlış refactoring**

### ❗ Interface sadece “katman” için mi?

→ **Premature abstraction**

### ❗ Test yazmak hâlâ zor mu?

→ **Sorun interface değil, sorumluluklarda**

---

# Extract Interface + TDD ilişkisi

TDD’de bu refactoring genelde şuradan gelir:

1. Concrete class ile başlarsın
2. Testte kontrol edemezsin
3. Fake / Stub ihtiyacı doğar
4. **Extract Interface**
5. Tipleri interface’e çevirirsin

> **Test → Değişim → Interface**

---

# Kısa özet (Kent Beck diliyle)

Extract Interface:

* Önce **isim** (gerekirse sınıfı yeniden adlandır)
* Sonra **contract**
* Sonra **implementasyon**
* En son **tipleri gevşet**

Ve altın kural:

> **Interface, değişim ekseni netleşmeden çıkmaz.**

---

**Extract Interface**, Kent Beck’in TDD yaklaşımında
**“ileride lazım olur” diye yapılan bir soyutlama değil**,
**testlerin ve değişimin zorladığı noktada ortaya çıkan bir refactoring**’dir.

---

## Extract Interface nedir?

Bir sınıfın **kullandığı davranışları** alıp
ayrı bir **interface** altında toplamak.

Ama Kent Beck için kritik fark şudur:

> ❌ “Interface olsun diye interface”
>
> ✅ “Değişim noktası görünür oldu, artık interface gerekli”

---

## Kent Beck’e göre ne zaman yapılır?

### 1️⃣ Test dublé ihtiyacı doğduğunda

TDD’de interface çoğu zaman **test yazmak için çıkar**.

```java
class PaymentService {
    private final BankGateway gateway;

    PaymentService(BankGateway gateway) {
        this.gateway = gateway;
    }
}
```

Burada soru şudur:

> “Testte bu bağımlılığı nasıl kontrol ederim?”

Cevap:
➡️ **Extract Interface**

---

### 2️⃣ Aynı davranışa birden fazla implementasyon geldiğinde

Kent Beck’in “**One implementation is not a pattern**” sözü burada devreye girer.

```java
interface BankGateway {
    void pay(int amount);
}
```

* Mock / Fake (testte)
* Gerçek banka entegrasyonu (prod)

---

### 3️⃣ Değişim ekseni netleştiğinde (Axis of Change)

Interface, **değişen şeyin etrafına çıkarılır**.

❌ Yanlış:

```java
interface AccountService { ... }
```

✔ Doğru:

```java
interface PaymentGateway { ... }
```

> Çünkü değişen “servis” değil, **ödeme şeklidir**.

---

## Extract Interface nasıl yapılır? (TDD akışıyla)

### 1️⃣ GREEN – Önce concrete sınıf

```java
class RealBankGateway {
    void pay(int amount) {
        // banka entegrasyonu
    }
}
```

---

### 2️⃣ Test zorlar – bağımlılık kontrol edilemiyor

```java
@Test
void shouldCallGateway() {
    PaymentService service =
        new PaymentService(new RealBankGateway()); // ❌
}
```

---

### 3️⃣ REFACTOR – Extract Interface

```java
interface BankGateway {
    void pay(int amount);
}

class RealBankGateway implements BankGateway {
    public void pay(int amount) {
        // gerçek entegrasyon
    }
}
```

---

### 4️⃣ Test rahatlar

```java
class FakeBankGateway implements BankGateway {
    boolean called = false;

    public void pay(int amount) {
        called = true;
    }
}
```

---

## Kent Beck açısından önemli uyarılar

### ❗ Interface ≠ Abstraction

Kent Beck der ki:

> “Her abstraction bir interface değildir.”

Bazen:

* Extract Method
* Extract Object

yeterlidir.

---

### ❗ Erken interface = teknik borç

Şu tip interface’ler **anti-pattern** sayılır:

```java
interface UserService {
    void create();
    void update();
    void delete();
}
```

➡️ Değişim ekseni yok
➡️ Test ihtiyacı yok
➡️ Sadece “katman” var

---

## Extract Interface ile ilişkili prensipler

* **Dependency Inversion**
* **Pluggable Object**
* **Test Double (Fake / Stub / Mock)**
* **Open–Closed Principle**

Ama Kent Beck için sırayla gelir:

> **Test → Değişim → Interface**

---

## Kısa özet

Kent Beck’e göre **Extract Interface**:

* Baştan yapılmaz
* “Temiz kod” olsun diye yapılmaz
* UML güzel dursun diye yapılmaz

👉 **Test yazmak zorlaştığında**
👉 **Değişim noktası netleştiğinde**
👉 **Concrete sınıf yük olmaya başladığında** yapılır.

---

