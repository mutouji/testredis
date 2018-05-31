package org.delphy.testredis.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class TestAutoCar {
    public static void main(String[] args) {
        IAuto car = new MyAutoCar();
        MethodBeforeAdvice adviceBefore = new BeforeAdviceDemo(); // like an interceptor
        AfterReturningAdvice adviceAfter = new BibiAdvice(); // like an after
        MethodInterceptor methodInterceptor = new AroundAdviceDemo();
        ThrowsAdvice throwsAdvice = new ThrowsAdviceDemo();
        DelegatingIntroductionInterceptor intelligentIntroduction = new IntelligentCar();
        DefaultIntroductionAdvisor defaultIntroductionAdvisor = new DefaultIntroductionAdvisor(intelligentIntroduction);

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(car);
        pf.setInterfaces(car.getClass().getInterfaces());
        pf.addAdvice(adviceAfter);
        pf.addAdvice(adviceBefore);
        pf.addAdvice(methodInterceptor);
        pf.addAdvice(throwsAdvice);
//        pf.addAdvice(intelligentIntroduction);
        pf.addAdvisor(defaultIntroductionAdvisor);
        IAuto proxy = (IAuto)pf.getProxy();
        proxy.driving();
        IIntelligent intelligent = (IIntelligent)proxy;
        intelligent.selfDriving();
    }
}
