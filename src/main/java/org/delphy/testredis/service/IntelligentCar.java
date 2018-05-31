package org.delphy.testredis.service;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class IntelligentCar extends DelegatingIntroductionInterceptor implements IIntelligent {

    @Override
    public void selfDriving() {
        System.out.println("开启无人驾驶了，别挡路");
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = super.invoke(invocation);
        return obj;
    }
}
