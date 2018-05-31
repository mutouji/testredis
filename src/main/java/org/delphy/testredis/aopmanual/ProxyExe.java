package org.delphy.testredis.aopmanual;

import java.lang.reflect.Proxy;

public class ProxyExe {
    public static void main(String[] args)
    {
        System.out.println("Proved.............");
        UserBeanImpl targetObject = new UserBeanImpl("Bob Liang");
        UserBeanProxy proxy = new UserBeanProxy(targetObject);
        //生成代理对象
        IUserBean object = (IUserBean) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), proxy);
        object.addUser();

        System.out.println("NO Proved.............");
        targetObject = new UserBeanImpl();
        proxy = new UserBeanProxy(targetObject);
        //生成代理对象
        object = (IUserBean)Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), proxy);
        object.addUser();
    }
}
