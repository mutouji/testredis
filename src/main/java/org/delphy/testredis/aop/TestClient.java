package org.delphy.testredis.aop;

import org.delphy.testredis.service.IUserService;
import org.delphy.testredis.service.UserService;
import org.delphy.testredis.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

//这样就保持了UserService业务的纯粹性，避免非业务代码和业务代码混合在一起。如果希望取消时间监控，只需要删除applicationContext里的<bean class="demo.aop.PerformanceMonitorUserService" />即可。
//
//        Spring是如何做到的呢？底层的两大功臣是JDK的动态代理和CGLib动态代理技术。我们以JDK的动态代理技术来重现上面的过程
public class TestClient {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.login("Tome", "123456");
    }

    public static void main2(String[] args) {
        IUserService target = new UserServiceImpl();
        PerformanceMonitorUserService2 handler = new PerformanceMonitorUserService2(target);
        IUserService proxy = (IUserService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
        proxy.login("Tom", "123456");
    }
}
