package org.delphy.testredis.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

//切面(Aspect):对横切关注点的抽象(类似类对对象的抽象)
//连接点(JoinPoint):被拦截到的点,泛指方法
//切入点(CutPoint):对哪些连接点进行拦截的定义
//通知(Advice):在特定的连接点，AOP框架执行的动作.前置/后置/例外/最终/环绕通知(调用方法之前执行,全部执行完毕之后)
//引入（Introduction）: 添加方法或字段到被通知的类。 Spring允许引入新的接口到任何被通知的对象。例如，你可以使用一个引入使任何对象实现 IsModified接口，来简化缓存。
//目标对象（Target Object）: 包含连接点的对象。也被称作 被通知或被代理对象。
//AOP代理（AOP Proxy）: AOP框架创建的对象，包含通知。 在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。
//织入（Weaving）: 组装方面来创建一个被通知对象。这可以在编译时 完成（例如使用AspectJ编译器），也可以在运行时完成。Spring和其他纯Java AOP框架一样， 在运行时完成织入。
@Aspect
public class PerformanceMonitorUserService {
    // 1. 可改装的地方叫连接点 Joinpoint === method‘s before after exception around，spring只支持方法的连接点
    // 2. 切点 Pointcut === "execution(* login(..))"
    // 3. 在目标连接点织入Weaving写好的代码，代码写在哪儿？卸载增强(Advice，或者叫通知中），增强并不能使随便一段代码，还必须配合连接点的方位，例如MethodBeforeAdvice就只能织入方法调用前的位置，AfterReturningAdvice只能织入方法返回后的位置
    // 4. 在掌握了可用的增强后，接下来要做的就是精确的描述切点。前面的示例都是指定一个目标类并把增强织入到所有方法中，实际开发显然会有更精细的筛选需求，
    //    比如对所有类中名称以test结尾的方法加入监控执行时间，或者指定某些方法仅在输入参数是指定值时做某些特殊处理以解决临时性需求。
    //    Advice包含了默认的切点信息，即目标类的所有方法
    // 5. Spring中用Pointcut接口表示一个切点，其下设有多个实现类，按使用场景分主要有静态切点、动态切点、流程切点和复合切点等。
    @Around("execution(* login(..))")
    public void aroundLogin(ProceedingJoinPoint pjp) {
        String userName = pjp.getArgs()[0].toString();
        long begin = System.nanoTime();
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("PerformanceMonitorUserService: 用户" + userName + "登录耗时" + TimeUnit.MILLISECONDS.convert((end - begin), TimeUnit.NANOSECONDS) + "毫秒");
    }
}
@Aspect
class PerformanceMonitorUserService2 implements InvocationHandler {

    private Object target;
    public PerformanceMonitorUserService2(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long begin = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        long end = System.currentTimeMillis();
        System.out.println("PerformanceMonitorUserService: 用户" + args[0] + "登录耗时" + TimeUnit.MILLISECONDS.convert((end - begin), TimeUnit.NANOSECONDS) + "毫秒");
        return obj;
    }
}
