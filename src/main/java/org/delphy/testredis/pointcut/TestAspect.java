package org.delphy.testredis.pointcut;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAspect {
    @Autowired
    private StorageAdvisor storageAdvisor;
//    <aop:aspectj-autoproxy />
//    <bean id="horseman" class="examples.chap03.Horseman" />
//    <bean id="swordman" class="examples.chap03.Swordman" />
//    <bean class="examples.chap03.StorageAdvisor" />
    public static void main(String[] args) {
        Horseman horseman = new Horseman();
        Swordman swordman = new Swordman();

        MethodBeforeAdvice adviceBefore = new BeforeAdvice();
        RegexpMethodPointcutAdvisor storageAdvisor = new RegexpMethodPointcutAdvisor();

        ProxyFactory pf1 = new ProxyFactory();
        pf1.setTarget(horseman);
        Class<?>[] interfaces = horseman.getClass().getInterfaces();
        pf1.setInterfaces(interfaces);
//        pf1.addAdvisor(storageAdvisor);
        Horseman proxy = (Horseman)pf1.getProxy();
        proxy.chop("big rat");
        proxy.rush("big rat");
    }
}
