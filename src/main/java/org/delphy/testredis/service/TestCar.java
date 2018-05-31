package org.delphy.testredis.service;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class TestCar {
    public static void main(String[] args) {
        ICar car = new MyCar();
        AfterReturningAdvice advice = new BibiAdvice();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(car);
        pf.setInterfaces(car.getClass().getInterfaces());
        pf.addAdvice(advice);
        ICar proxy = (ICar)pf.getProxy();
        proxy.lock();
    }
}
