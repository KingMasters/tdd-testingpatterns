Tamam, **Kent Beck TDD aklıyla** adım adım gösteriyorum.
Aynı kod üzerinde **Red → Green → Extract Method → Inline Method → Object Extraction** zincirini net biçimde göreceksin.

---

# 1️⃣ RED – Test davranışı zorlar

Önce davranışı tarif eden test:

```java
@Test
void shouldWithdrawMoneyWhenBalanceIsSufficient() {
    Account account = new Account(100);

    account.withdraw(40);

    assertEquals(60, account.getBalance());
}
```

Bir de hata durumu:

```java
@Test
void shouldThrowExceptionWhenBalanceIsInsufficient() {
    Account account = new Account(50);

    assertThrows(InsufficientBalanceException.class,
            () -> account.withdraw(60));
}
```

---

# 2️⃣ GREEN – Çirkin ama çalışıyor

Testleri geçirmek için **en basit kod**:

```java
class Account {

    private int balance;

    Account(int balance) {
        this.balance = balance;
    }

    void withdraw(int amount) {
        if (balance < amount) {
            throw new InsufficientBalanceException();
        }
        balance -= amount;
    }

    int getBalance() {
        return balance;
    }
}
```

Kent Beck: ✔️
Kod güzel değil, **ama testler yeşil**.

---

# 3️⃣ EXTRACT METHOD – Niyeti ortaya çıkar

Şimdi **davranışlara isim verelim**:

```java
void withdraw(int amount) {
    validateBalance(amount);
    decreaseBalance(amount);
}

private void validateBalance(int amount) {
    if (balance < amount) {
        throw new InsufficientBalanceException();
    }
}

private void decreaseBalance(int amount) {
    balance -= amount;
}
```

### Burada ne oldu?

* `if (balance < amount)` → **bir kavram oldu**
* `balance -= amount` → **bir davranış oldu**

Bu tam olarak Kent Beck’in

> **“Reveal Intent”** dediği şey.

---

# 4️⃣ INLINE METHOD – Yanlış soyutlamayı sil

Bir süre sonra şunu fark ediyorsun:

* `decreaseBalance` **hiçbir anlam katmıyor**
* Sadece tek satır
* Başka yerde kullanılmıyor

Kent Beck der ki:

> “Yanlış soyutlama varsa, sil.”

### Inline ediyoruz 👇

```java
void withdraw(int amount) {
    validateBalance(amount);
    balance -= amount;
}
```

🧠 **Önemli nokta**
Extract yapmak hata değildir.
Ama **gereksiz extract’te ısrar etmek hatadır**.

---

# 5️⃣ OBJECT EXTRACTION – Gerçek tasarım ortaya çıkar

Zamanla yeni test geliyor:

```java
@Test
void shouldCompareBalances() {
    Money money1 = new Money(100);
    Money money2 = new Money(40);

    assertTrue(money1.isGreaterThan(money2));
}
```

Artık şunu görüyorsun:

* `int` **yetersiz**
* Para kendi davranışlarını istiyor

### Money Value Object çıkıyor

```java
class Money {

    private final int amount;

    Money(int amount) {
        this.amount = amount;
    }

    boolean isLessThan(Money other) {
        return this.amount < other.amount;
    }

    Money minus(Money other) {
        return new Money(this.amount - other.amount);
    }
}
```

---

# 6️⃣ Account yeni haline evrilir

```java
class Account {

    private Money balance;

    Account(int amount) {
        this.balance = new Money(amount);
    }

    void withdraw(int amount) {
        Money money = new Money(amount);
        validateBalance(money);
        balance = balance.minus(money);
    }

    private void validateBalance(Money money) {
        if (balance.isLessThan(money)) {
            throw new InsufficientBalanceException();
        }
    }

    int getBalance() {
        return balance.getAmount();
    }
}
```

---

# 🧠 Kent Beck bakış açısının özeti

Bu zincirde **önceden tasarım yok**:

1. Test yazdı
2. Kod çalıştı
3. Extract ile **anlam çıktı**
4. Inline ile **yanlış anlam silindi**
5. Object extraction ile **doğru model doğdu**

Kent Beck buna şunu der:

> **“Design is not decided, it is discovered.”**

---

## Kısa sloganla kapatalım

* **Extract** → “Bunun adı ne?”
* **Inline** → “Aslında bu bir şey değilmiş”
* **TDD** → “Testler gerçeği söyler”

