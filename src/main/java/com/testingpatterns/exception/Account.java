package com.testingpatterns.exception;

public class Account {

    private int balance;

    public Account(int initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(int amount) {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Current balance: " + balance
            );
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}

