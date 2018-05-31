package org.delphy.testredis.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundAdviceDemo implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] args = invocation.getArguments();
        String name = "name is null";
        if (args.length > 0) {
            name = (String)args[0];
        }
        System.out.println("Hi," + name);
        Object obj = invocation.proceed(); // 调用目标方法
        System.out.println("Goodbye! " + name);
        return obj;
    }
}
