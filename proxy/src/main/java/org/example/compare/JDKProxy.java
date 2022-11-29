package org.example.compare;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理类
 */
public class JDKProxy implements InvocationHandler {
    /**
     * 需要代理的目标对象
     */
    private Object targetObject;

    /**
     * 将目标对象进行代理
     *
     * @param targetObject
     * @return
     */
    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;
        //返回代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy before code");
        Object result = method.invoke(targetObject, args);
        System.out.println("jdk proxy after code");
        return result;
    }
}
