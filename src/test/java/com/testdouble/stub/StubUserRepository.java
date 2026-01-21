package com.testdouble.stub;

import com.testdouble.User;
import com.testdouble.UserRepository;

/*
    Stub = “çağrıldığında önceden belirlenmiş cevap döndürür”
    Ne zaman?
        Sadece girdi → çıktı ile ilgileniyorsan
        Davranışı doğrulamak istemiyorsan

 */
public class StubUserRepository implements UserRepository {
    @Override
    public User findById(String id) {
        return new User("42", "Stub User", "stub@email.com");
    }

    @Override
    public void save(User user) {
        return;
    }
}
