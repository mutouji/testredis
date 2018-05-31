package org.delphy.testredis.service;

public class MySmartCar implements ISmartCar {

    @Override
    public void lock(String userName) {
        if (userName.equals("Careless John")) {
            throw new RuntimeException(userName + "忘记锁车");
        } else {
            System.out.println(userName + "锁车");
        }
    }
}
