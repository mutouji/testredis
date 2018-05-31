package org.delphy.testredis.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class TestSmartCar {
    public static void main(String[] args) {
        ISmartCar car = new MySmartCar();
        MethodBeforeAdvice adviceBefore = new BeforeAdviceDemo(); // like an interceptor
        AfterReturningAdvice adviceAfter = new BibiAdvice(); // like an after
        MethodInterceptor methodInterceptor = new AroundAdviceDemo();
        ThrowsAdvice throwsAdvice = new ThrowsAdviceDemo();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(car);
        pf.setInterfaces(car.getClass().getInterfaces());
        pf.addAdvice(adviceAfter);
        pf.addAdvice(adviceBefore);
        pf.addAdvice(methodInterceptor);
        pf.addAdvice(throwsAdvice);
        ISmartCar proxy = (ISmartCar)pf.getProxy();
        proxy.lock("duoduo");
    }
}
