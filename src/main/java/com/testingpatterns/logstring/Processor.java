package com.testingpatterns.logstring;

public class Processor {
    private final Listener listener;

    Processor(Listener listener) {
        this.listener = listener;
    }

    void process() {
        listener.start();
        listener.finish();
    }
}
