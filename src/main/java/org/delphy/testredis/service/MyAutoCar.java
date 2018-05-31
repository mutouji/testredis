package org.delphy.testredis.service;

public class MyAutoCar implements IAuto {
    @Override
    public void driving() {
        System.out.println("开车了");
    }
}
