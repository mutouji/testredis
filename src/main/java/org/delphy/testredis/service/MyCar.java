package org.delphy.testredis.service;

public class MyCar implements ICar {
    @Override
    public void lock() {
        System.out.println("锁车");
    }
}
