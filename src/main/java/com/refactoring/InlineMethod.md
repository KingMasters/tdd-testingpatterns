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
