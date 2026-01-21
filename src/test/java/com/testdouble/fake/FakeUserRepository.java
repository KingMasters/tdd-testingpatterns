package com.testdouble.fake;

import com.testdouble.User;
import com.testdouble.UserRepository;

import java.util.HashMap;
import java.util.Map;
/*
    Fake
        Çalışır ama basitleştirilmiş gerçek implementasyon
        Fake = “gerçek gibi davranır ama production için uygun değildir”
    Ne zaman?
        Gerçek implementasyon yavaş / pahalı / karmaşık ise
        In-memory DB gibi

   📌 Özellikleri
        Gerçek davranış var
        Assertion yapmaz
        State tutar
        Testte rahat kullanılır
 */
public class FakeUserRepository implements UserRepository {

    private final Map<String, User> data = new HashMap<>();

    @Override
    public void save(User user) {
        data.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return data.get(id);
    }
}
