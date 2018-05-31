package org.delphy.testredis.service;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class ThrowsAdviceDemo implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("捕获异常:" + ex.getMessage());
        System.out.println("(报警)滴滴滴滴滴滴滴滴滴滴滴滴");
    }
}
