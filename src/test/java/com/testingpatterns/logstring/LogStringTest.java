package com.testingpatterns.logstring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogStringTest {
    private String log = "";

    @Test
    void callsMethodsInOrder() {
        Listener listener = new Listener() {
            @Override
            public void start() {
                log += "start ";
            }

            @Override
            public void finish() {
                log += "finish ";
            }
        };

        Processor processor = new Processor(listener);

        processor.process();

        assertEquals("start finish ", log);
    }
}

