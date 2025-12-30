package com.testingpatterns.fixture;

public class Token {

    private static final long TTL = 60_000L;
    private final long createdAt;
    private final Clock clock;

    public Token(Clock clock) {
        this.clock = clock;
        this.createdAt = clock.now();
    }

    public boolean isExpired() {
        return clock.now() - createdAt > TTL;
    }
}

