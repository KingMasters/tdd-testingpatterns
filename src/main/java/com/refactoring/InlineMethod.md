Evet, şimdi **aynı Kent Beck çizgisinde**, daha önce anlattığım **Inline Method**’u
**senin verdiğin “How” adımlarını birebir merkeze alarak** tekrar ve **daha net** biçimde açıklıyorum.

Bu sefer özellikle **neden bu kadar mekanik tarif ettiğini** de göreceksin.

---

# Kent Beck’e göre **Inline Method**

## Inline Method nedir? (kısa hatırlatma)

Kent Beck için Inline Method:

* “Kodu kısaltma” tekniği değildir
* **Yanlış veya gereksiz soyutlamayı silme** refactoring’idir

Temel soru:

> **“Bu metot gerçekten bir kavram mı?”**

Cevap **hayır** ise → **Inline Method**

---

# Şimdi Beck’in verdiğin **HOW** adımlarını açalım

---

## 1️⃣ Copy the method

Neden kopyala?

Kent Beck’in refleksi:

> **Davranışı bozmadan ilerle**

Bu yüzden:

* Metodu **silmezsin**
* Önce **kopyalarsın**

Bu, TDD’de çok kritiktir çünkü:

* Testler yeşil kalır
* Geri dönüş mümkündür

---

## 2️⃣ Paste the method over the method invocation

Yani:

```java
total += calculateTax(price);
```

Metodu alıp **çağrının yerine yapıştırırsın**:

```java
total += price * 0.18;
```

Burada önemli nokta:

* Artık **dolaylılık yok**
* Davranış **doğrudan görünür**

Kent Beck için Inline Method’un özü budur:

> **Indirection’ı ortadan kaldır**

---

## 3️⃣ Replace all formal parameters with actual parameters

Bu adım en kritik ve en çok hata yapılan yer.

### Basit örnek

```java
int calculateTax(int price) {
    return price * 0.18;
}
```

Çağrı:

```java
total += calculateTax(price);
```

Inline sonrası:

```java
total += price * 0.18;
```

---

## ⚠️ Side effect uyarısı (Beck’in özellikle belirttiği nokta)

Beck özellikle şunu söyler:

> *If you pass reader.getNext() (an expression causing side effects)…*

### Tehlikeli örnek ❌

```java
process(read());
```

Metot:

```java
void process(Item item) {
    save(item);
}
```

Yanlış inline:

```java
save(read()); // read() tekrar çağrılabilir, side effect!
```

---

### Doğru inline ✔

```java
Item item = read();
save(item);
```

Yani:

* **Actual parameter** side effect içeriyorsa
* Önce **local variable’a al**
* Sonra inline et

Kent Beck’in bu kadar mekanik anlatmasının sebebi:

> **Davranışı fark etmeden değiştirmemek**

---

## Inline Method sonrası ne olur?

Genelde şu olur:

* Orijinal metot **boşalır**
* Ya tamamen silinir
* Ya da tek satırlık delegator olur

```java
int calculateTax(int price) {
    return price * 0.18;
}
```

➡️ Silinir.

---

# Inline Method ne zaman YAPILMAZ?

Kent Beck burada da net:

### ❌ Metot gerçek bir kavramsa

```java
authorizePayment()
```

### ❌ Metot değişim noktasıysa

```java
calculateDiscount()
```

### ❌ Metot testlerde anlamlıysa

> “Bu metodu mock’luyorum çünkü davranışı temsil ediyor”

---

# Inline ↔ Extract dengesi (Beck düşüncesi)

Kent Beck’e göre tasarım:

* Sürekli **Extract**
* Sonra **Inline**
* Sonra tekrar **Extract**

> Tasarım doğrusal ilerlemez, **salınır**.

---

# Kısa özet (Kent Beck diliyle)

Inline Method:

* Kod azaltmak için değil
* Performans için değil

👉 **Gereksiz soyutlamayı silmek**
👉 **Davranışı çıplak görmek** içindir.

Ve altın kural:

> **Inline ederken, side effect’leri görünür kıl.**

---

---

Kent Beck’in **TDD (Test-Driven Development)** yaklaşımında **Inline Method**, çoğu kişinin düşündüğünün tersine
“geri adım” değil, **tasarımı sadeleştirme (design cleanup)** hamlesidir.

---

## Inline Method nedir?

**Inline Method**, bir metodun çağrıldığı yere **geri gömülmesi** ve metodun kaldırılmasıdır.

Yani:

* Metot **anlam katmıyorsa**
* Sadece başka bir metodu çağırıyorsa
* Yanlış bir soyutlama oluşturuyorsa

→ **inline edilir**.

---

## Kent Beck’e göre asıl amacı

Kent Beck’in temel ilkesi şudur:

> **“En basit şeyi yap.”**
> *(Do the simplest thing that could possibly work)*

Inline Method bu ilkenin **refactoring karşılığıdır**.

### Amaçlar:

1. **Yanlış soyutlamayı yok etmek**
2. **Gereksiz dolaylılığı (indirection) kaldırmak**
3. **Okunabilirliği artırmak**
4. **Tasarımı gerçek ihtiyaca geri çekmek**

---

## Ne zaman Inline Method yapılır?

### 1. Metot hiçbir “niyet” taşımıyorsa

```java
boolean isValid() {
    return check();
}
```

Bu, Kent Beck’e göre **fazlalık**tır.

✔ Doğrusu:

⬅️ `check()` inline edilir ve silinir.

---

### 2. Erken çıkarılmış (premature) metotlar

TDD’de bazen **ileride lazım olur** diye metot çıkarılır.

```java
void process() {
    validate();
    execute();
}
```

Ama testler şunu gösterir:

* `validate()` başka yerde kullanılmıyor
* Ayrı bir kavram oluşturmuyor

➡️ **Inline edilir**.

---

### 3. Testten kaçmak için oluşturulan metotlar

Bazen metot sadece şunu yapar:

* test yazmayı zorlaştırmak
* private logic’i saklamak

Kent Beck buna sıcak bakmaz.

> “Test yazmak zorlaşıyorsa, tasarım yanlıştır.”

Inline Method ile **gerçek akış** görünür olur.

---

## Extract Method ile ilişkisi

Kent Beck açısından **Extract ↔ Inline** sürekli gidip gelen bir çifttir:

| Extract Method    | Inline Method       |
| ----------------- | ------------------- |
| Anlam çıkar       | Anlamsızı yok et    |
| Niyet ortaya koy  | Yanlış niyeti sil   |
| Tasarımı genişlet | Tasarımı sadeleştir |

> TDD’de tasarım **monoton artmaz**, dalgalanır.

---

## Inline Method TDD döngüsünde nerede?

Yine **Refactor** aşamasında:

1. 🔴 Red
2. 🟢 Green
3. 🔵 Refactor
   → **Extract** veya **Inline**

Kent Beck için:

> “Refactor etmek sadece eklemek değildir; **silmek de refactoringdir**.”

---

## Küçük ama önemli Kent Beck prensibi

Bir metot şu soruya cevap vermiyorsa:

> **“Bu metodu okuyan biri, yeni bir kavram öğreniyor mu?”**

Cevap **hayır** ise → **Inline Method**.

---

## Özet

Kent Beck’e göre **Inline Method**:

* Kod kısaltmak için değil
* Performans için değil

👉 **Yanlış soyutlamayı silmek**
👉 **Gereksiz dolaylılığı kaldırmak**
👉 **“En basit tasarıma” geri dönmek** içindir.

---
