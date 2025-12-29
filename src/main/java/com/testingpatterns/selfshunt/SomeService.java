package com.testingpatterns.selfshunt;

public class SomeService {
    private final ResultListener listener;

    SomeService(ResultListener listener) {
        this.listener = listener;
    }

    public void doWork() {
        listener.notifySuccess();
    }


}
