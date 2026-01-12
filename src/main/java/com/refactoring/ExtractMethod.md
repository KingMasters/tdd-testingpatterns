Evet — şimdi **daha önce anlattığım Kent Beck’e göre Extract Method** açıklamasını,
**senin verdiğin “How” maddelerini birebir merkeze alarak**, aynı çizgide ve sistematik biçimde tekrar anlatıyorum.

Aşağıda hem **mekanik adımlar** hem de **Beck’in neden böyle tarif ettiğini** birlikte göreceksin.

---

# Kent Beck’e göre **Extract Method**

## Extract Method nedir? (kısa hatırlatma)

Kent Beck için Extract Method:

* “Uzun metodu bölmek” tekniği değildir
* **Davranışa isim vermek** ve
* **Tasarımdaki kavramları görünür kılmak** için yapılır

Temel soru:

> **“Bu kod parçası bağımsız bir anlam taşıyor mu?”**

Evet → **Extract Method**

---

# Şimdi Beck’in verdiğin **HOW** adımlarını tek tek açalım

---

## 1️⃣ Find a region of the method that would make sense as its own method

Beck burada özellikle şunu söyler:

> Döngü gövdeleri, tüm döngüler ve koşul dalları
> **extract için doğal adaylardır**

### Örnek (önce)

```java
void withdraw(int amount) {
    if (balance < amount) {
        throw new InsufficientBalanceException();
    }
    balance -= amount;
}
```

Burada şu soru sorulur:

* `if` bloğu **kendi başına bir kavram mı?**
  → Evet: “bakiye doğrulama”

---

## 2️⃣ Make sure there are no assignments to temporary variables declared outside

Bu madde **çok kritik** ve Kent Beck’in neden dikkatli olduğunu gösterir.

### Neden?

Çünkü:

* Dış scope’taki temporary variable’a yazıyorsan
* Extract sonrası **davranış farkında olmadan değişebilir**

### Tehlikeli örnek ❌

```java
int total = 0;
for (Item item : items) {
    total += item.price();
}
```

Burayı direkt extract etmek **tehlikelidir** çünkü:

* `total` dış scope’ta
* Extract edilen metot onu **değiştiriyor**

---

### Beck’in önerdiği doğru yol ✔

```java
int total = calculateTotal(items);

int calculateTotal(List<Item> items) {
    int total = 0;
    for (Item item : items) {
        total += item.price();
    }
    return total;
}
```

> Beck burada şunu ister:
> **State açıkça girsin, açıkça çıksın**

---

## 3️⃣ Copy the code from the old method to the new method. Compile it.

Yine tanıdık refleks:

> **Önce çalıştır, sonra temizle**

* Kodu kopyala
* Yeni metodu oluştur
* Compile et

Bu aşamada:

* Davranış **henüz taşınmadı**
* Sadece **kopyalandı**

---

## 4️⃣ Add parameters for each temporary variable or parameter used

Yeni metot:

* Orijinal metottaki

    * parametreleri
    * local variable’ları
      kullanıyorsa → **parameter olarak alır**

### Örnek

```java
void withdraw(int amount) {
    validateBalance(amount, balance);
    balance -= amount;
}

void validateBalance(int amount, int balance) {
    if (balance < amount) {
        throw new InsufficientBalanceException();
    }
}
```

Kent Beck için bu adım çok değerlidir çünkü:

> **Bağımlılıklar görünür hale gelir**

---

## 5️⃣ Call the new method from the original method

Son adım:

* Orijinal kodu sil
* Yerine **metot çağrısı koy**

```java
void withdraw(int amount) {
    validateBalance(amount, balance);
    balance -= amount;
}
```

Artık:

* Orijinal metot **orkestratör**
* Yeni metot **davranışın kendisi**

---

# Kent Beck neden bu kadar mekanik anlatıyor?

Çünkü:

* Extract Method **davranış eklemez**
* **Davranışı yeniden düzenler**

Beck’in korktuğu şey:

> “Refactor ederken gizlice bug sokmak”

Bu adımlar:

* Her an compile edilebilir
* Her an testler yeşil kalır

---

# Extract Method ne zaman YAPILMAZ?

Kent Beck burada da nettir:

### ❌ Kod parçası bir kavram oluşturmuyorsa

### ❌ Sadece “uzun” olduğu için

### ❌ Dış state’i gizlice değiştiriyorsa

---

# Extract Method → Sonrası ne olur?

Çok sık şu olur:

* Yeni metot **başka sınıfa taşınır** → Move Method
* Birden fazla extract → **Object Extraction**
* Gereksiz extract → **Inline Method**

Yani Extract Method:

> **Tasarımın ilk kıpırtısıdır**

---

# Kısa özet (Kent Beck diliyle)

Extract Method:

* Kodu bölmek için değil
* Temizlik olsun diye değil

👉 **Davranışa isim vermek**
👉 **Bağımlılıkları görünür kılmak**
👉 **Sonraki tasarım adımlarını mümkün kılmak** içindir.

---

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

