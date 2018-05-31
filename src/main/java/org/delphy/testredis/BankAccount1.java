package org.delphy.testredis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount1 {
    double balance;
    final int id;
    final Lock lock = new ReentrantLock();

    BankAccount1(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    void withdraw(double amount) {
        // Wait to simulate io like database access ...
        try {Thread.sleep(10l);} catch (InterruptedException e) {}
        balance -= amount;
    }

    void deposit(double amount) {
        // Wait to simulate io like database access ...
        try {Thread.sleep(10l);} catch (InterruptedException e) {}
        balance += amount;
    }

    static void transfer(BankAccount1 from, BankAccount1 to, double amount) {
        from.lock.lock();
        from.withdraw(amount);
        to.lock.lock();
        to.deposit(amount);
        to.lock.unlock();
        from.lock.unlock();
    }

    public static void main(String[] args) {
        final BankAccount1 fooAccount = new BankAccount1(1, 100d);
        final BankAccount1 barAccount = new BankAccount1(2, 100d);

        new Thread() {
            public void run() {
                BankAccount1.transfer(fooAccount, barAccount, 10d);
            }
        }.start();

        new Thread() {
            public void run() {
                BankAccount1.transfer(barAccount, fooAccount, 10d);
            }
        }.start();

    }
}
