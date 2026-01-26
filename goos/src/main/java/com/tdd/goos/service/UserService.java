package com.tdd.goos.service;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<String> emails = new ArrayList<>();

    public void create(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email must not be null or blank");
        }
        emails.add(email);
    }

    public List<String> getAllEmails() {
        return new ArrayList<>(emails);
    }
}

