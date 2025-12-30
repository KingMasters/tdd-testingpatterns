package com.designpattern.command;

public class WithdrawCommand implements Command {

    private final Account account;
    private final int amount;

    public WithdrawCommand(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount);
    }
}
