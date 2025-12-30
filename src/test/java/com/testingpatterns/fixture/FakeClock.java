package com.testingpatterns.fixture;

public class FakeClock implements Clock {
    private long now;

    FakeClock(long now) {
        this.now = now;
    }

    void advance(long millis) {
        now += millis;
    }

    @Override
    public long now() {
        return now;
    }
}