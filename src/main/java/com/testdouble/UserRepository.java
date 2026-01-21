package com.testdouble;

public interface UserRepository {
    void save(User user);
    User findById(String id);
}
