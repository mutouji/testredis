package org.delphy.testredis;

public class User {
    public int money;
    String firstName;
    String lastName;
    static java.util.concurrent.locks.Lock moneyLocker;

    public void filterSameUser(User other) {
        synchronized (this) {
            synchronized (other) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
