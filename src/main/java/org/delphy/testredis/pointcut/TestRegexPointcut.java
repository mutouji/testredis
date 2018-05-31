package org.delphy.testredis.pointcut;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

public class TestRegexPointcut {
//<bean id="beforeAdvice" class="examples.chap01.BeforeAdvice" />
//<bean id="horsemanTarget" class="examples.chap01.Horseman" />
//<bean id="swordmanTarget" class="examples.chap01.Swordman" />
//<bean id="pointcut" class="examples.chap02.DynamicStoragePointcut"
//    p:methodOption="1"
//    p:classOption="0" />
//<bean id="advisor" class="org.springframework.aop.support.DefaultPointcutAdvisor"
//    p:advice-ref="beforeAdvice"
//    p:pointcut-ref="pointcut" />
//
//<bean id="horseman" class="org.springframework.aop.framework.ProxyFactoryBean"
//    p:target-ref="horsemanTarget"
//    p:interceptorNames="advisor"
//    p:proxyTargetClass="true" />
//<bean id="swordman" class="org.springframework.aop.framework.ProxyFactoryBean"
//    p:target-ref="swordmanTarget"
//    p:interceptorNames="advisor"
//    p:proxyTargetClass="true" />
    public static void main(String[] args) {
        Horseman horseman = new Horseman();
        Swordman swordman = new Swordman();

        MethodBeforeAdvice adviceBefore = new BeforeAdvice();
//        StoragePointcut pointcut = new StoragePointcut();
//        pointcut.setMethodOption(2);
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor(".*chop", adviceBefore);

        ProxyFactory pf1 = new ProxyFactory();
        pf1.setTarget(horseman);
        Class<?>[] interfaces = horseman.getClass().getInterfaces();
        pf1.setInterfaces(interfaces);
        pf1.addAdvisor(advisor);
        Horseman proxy = (Horseman)pf1.getProxy();
        proxy.chop("big rat");
        proxy.rush("big rat");

        ProxyFactory pf2 = new ProxyFactory();
        pf2.setTarget(swordman);
        pf2.addAdvisor(advisor);
        pf2.setInterfaces(swordman.getClass().getInterfaces());
        Swordman proxy2 = (Swordman)pf2.getProxy();
        proxy2.chop("big cat");
        proxy2.block("big cat");

    }
}
