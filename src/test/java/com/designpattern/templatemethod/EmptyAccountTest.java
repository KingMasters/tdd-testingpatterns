package com.designpattern.templatemethod;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyAccountTest extends AccountTestTemplate {

    @Override
    protected Account createAccount() {
        return new Account(0);
    }

    @Override
    protected void assertBalance() {
        assertEquals(0, account.getBalance());
    }
}
