package org.delphy.testredis.pointcut;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Advice:" + target.getClass().getSimpleName() + "蓄力");
    }
}
