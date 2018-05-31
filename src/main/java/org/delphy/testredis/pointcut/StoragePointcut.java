package org.delphy.testredis.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

// 1 静态方法切面

//        使用静态方法切点，通常是继承StaticMethodMatcherPointcut，通过覆盖getClassFilter()方法用于确定匹配的类，覆盖matches方法用于确定匹配的方法，Spring对目标类及其上面的方法调用两个方法以确定是否织入增强，检查结果被缓存以提高性能。来看示例：
public class StoragePointcut extends StaticMethodMatcherPointcut {
    private int methodOption;
    private int classOption;

    public void setMethodOption(int methodOption) {
        this.methodOption = methodOption;
    }

    public void setClassOption(int classOption) {
        this.classOption = classOption;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        switch (methodOption) {
            case 1:
                return "chop".equals(method.getName());
            case 2:
                return "rush".equals(method.getName());
            default:
                return true;
        }
    }

    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                switch (classOption) {
                    case 1:
                        return (Horseman.class.isAssignableFrom(clazz));
                    case 2:
                        return (Swordman.class.isAssignableFrom(clazz));
                    default:
                        return true;
                }
            }
        };
    }
}
