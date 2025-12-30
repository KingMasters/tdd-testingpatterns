package com.designpattern.command;

import com.testingpatterns.exception.InsufficientBalanceException;

public class Account {

    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(int amount) {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}
