package com.testingpatterns.crashtestdummy;

public class PaymentService {
    private final Logger logger;

    public PaymentService(Logger logger) {
        this.logger = logger;
    }

    public void pay(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        logger.log("paid: " + amount);
    }
}
