package org.delphy.testredis.aopmanual;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserBeanProxy implements InvocationHandler {
    private Object targetObject;
    public UserBeanProxy(Object targetObject) {
        this.targetObject = targetObject;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        UserBeanImpl userBean = (UserBeanImpl) targetObject;
        String userName = userBean.getUserName();
        Object result = null;

        // 权限判断
        if (userName != null && !"".equals(userName))
        {
            try {
                // before
                result = method.invoke(targetObject, args); // After-returning
                // after
            } catch (Exception e) {
                // after throwing
                e.printStackTrace();
            }
        }

        return result;
    }
}
