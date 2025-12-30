package com.designpattern.templatemethod;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveAccountTest extends AccountTestTemplate {

    @Override
    protected Account createAccount() {
        return new Account(100);
    }

    @Override
    protected void assertBalance() {
        assertEquals(90, account.getBalance());
    }
}
