package backend;

import java.io.Serializable;

public class Balance implements Serializable {
    private double balance;

    public Balance(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getTotalBalance() {
        return balance;
    }

    public boolean deduct(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addFunds(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    @Override
    public String toString() {
        return String.format("Balance: %.2f$", balance);
    }
}
