package com.designpattern.command;

public class DepositCommand implements Command {

    private final Account account;
    private final int amount;

    public DepositCommand(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }
}
