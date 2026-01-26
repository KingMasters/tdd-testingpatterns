package com.tdd.goos.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void createAddsEmail() {
        UserService s = new UserService();
        s.create("a@b.com");
        assertEquals(1, s.getAllEmails().size());
        assertEquals("a@b.com", s.getAllEmails().get(0));
    }

    @Test
    void createRejectsBlank() {
        UserService s = new UserService();
        assertThrows(IllegalArgumentException.class, () -> s.create("  "));
    }
}

