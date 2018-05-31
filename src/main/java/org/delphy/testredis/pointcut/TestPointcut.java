package org.delphy.testredis.pointcut;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class TestPointcut {
//    Car car = new MyCar();
//    AfterReturningAdvice advice = new BibiAdvice();
//    ProxyFactory pf = new ProxyFactory();
//        pf.setTarget(car);
//        pf.setInterfaces(car.getClass().getInterfaces());
//        pf.addAdvice(advice);
//    Car proxy = (Car)pf.getProxy();
//        proxy.lock();

//        <bean id="beforeAdvice" class="demo.aop.BeforeAdviceDemo" />
//    <bean id="afterAdvice" class="demo.aop.AfterAdviceDemo" />
//    <bean id="target" class="demo.aop.MyCar" />
//
//    <bean id="myCar" class="org.springframework.aop.framework.ProxyFactoryBean"
//    p:target-ref="target"
//    p:proxyTargetClass="true">
//        <property name="interceptorNames">
//            <list>
//                <idref local="beforeAdvice" />
//                <idref local="afterAdvice" />
//            </list>
//        </property>
//    </bean>


//    <bean id="carTarget" class="demo.aop.MyCar" />
//    <bean id="introduceAdvisor" class="org.springframework.aop.support.DefaultIntroductionAdvisor">
//        <constructor-arg>
//            <bean class="demo.aop.IntelligentCar" />
//        </constructor-arg>
//    </bean>
//    <bean id="myCar" class="org.springframework.aop.framework.ProxyFactoryBean"
//    p:target-ref="carTarget"
//    p:interceptorNames="introduceAdvisor"
//    p:proxyTargetClass="true" />
    public static void main(String[] args) {
        Horseman horseman = new Horseman();
        Swordman swordman = new Swordman();

        MethodBeforeAdvice adviceBefore = new BeforeAdvice();
        StoragePointcut pointcut = new StoragePointcut();
        pointcut.setMethodOption(1);
        pointcut.setClassOption(2);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, adviceBefore);

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
