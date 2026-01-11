Kent Beck’in **TDD (Test-Driven Development)** yaklaşımında **Extract Method**, yalnızca bir *refactoring* tekniği değil; **tasarımı ortaya çıkarmanın (emergent design)** en temel araçlarından biridir.

Aşağıda **TDD bağlamında** ne anlama geldiğini netleştireyim:

---

## Extract Method nedir?

**Extract Method**, uzun, karmaşık veya birden fazla sorumluluk taşıyan bir kod bloğunu alıp
**anlamlı bir isimle ayrı bir metoda çıkarmaktır.**

Ama Kent Beck için asıl mesele **kodun kısalması değil**, **niyetin açığa çıkmasıdır**.

---

## Kent Beck’e göre amacı

Kent Beck TDD’de sürekli şunu vurgular:

> “**Önce çalıştır, sonra temizle.**”
> *(Make it work → Make it right)*

Extract Method, bu **“temizleme”** adımının en güçlü silahıdır.

### 1. Niyeti görünür kılar (Reveal Intent)

```java
if (balance < amount) {
    throw new InsufficientBalanceException();
}
```

Bu kodu şöyle ayırdığında:

```java
if (isBalanceInsufficient(amount)) {
    throw new InsufficientBalanceException();
}
```

Artık **nasıl** değil, **ne** yaptığı okunur.

➡️ Testler davranışı doğrular,
➡️ Extract Method davranışın **adını** koyar.

---

### 2. Sorumlulukları ayırır (Single Responsibility)

TDD sırasında genelde bir metot şuna dönüşür:

* kontrol
* hesaplama
* doğrulama
* state değişimi

Extract Method ile her biri ayrılır:

```java
void withdraw(int amount) {
    validateBalance(amount);
    decreaseBalance(amount);
}
```

Bu, **SRP’nin doğal olarak oluşmasıdır**.

---

### 3. Testi yazdıran tasarımı ortaya çıkarır

Kent Beck’in ünlü sözü:

> “**Testler tasarımı zorlar.**”

Bir test yazarken şunu fark edersin:

> “Bu davranışın adı ne?”

İşte o isim → **Extract Method** ile doğar.

---

## TDD döngüsünde yeri

Extract Method genellikle şu aşamada yapılır:

1. 🔴 **Red** – Test yaz, fail et
2. 🟢 **Green** – En basit kodla geç
3. 🔵 **Refactor** – **Extract Method burada**

> TDD’de Extract Method **asla davranış eklemek için değil**,
> **davranışı netleştirmek için yapılır.**

---

## Kent Beck açısından önemli bir nokta

Extract Method genelde şunlara kapı açar:

* **Object extraction** (sonradan sınıf olur)
* **Pluggable Object**
* **Strategy / Command**
* **Value Object**

Yani:

> “Bugün method, yarın object.”

Kent Beck bu yüzden Extract Method’ı
**“tasarımın ilk sinyali”** olarak görür.

---

## Kısa özet

Kent Beck’e göre **Extract Method**:

* Kod kısaltmak için değil
* “Temizlik” olsun diye değil

👉 **Davranışa isim vermek**
👉 **Sorumlulukları ayırmak**
👉 **Testlerin dayattığı tasarımı görünür kılmak** için yapılır.

---

