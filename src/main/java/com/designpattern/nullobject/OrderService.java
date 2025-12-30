package com.designpattern.nullobject;

class OrderService {
    private final Logger logger;

    OrderService(Logger logger) {
        this.logger = logger;
    }

    void placeOrder() {
        //Bu kontrole gerek kalmadı NullLogger yazıldığı için
        //if (logger != null) {
            logger.log("order placed");
        //}
    }
}

