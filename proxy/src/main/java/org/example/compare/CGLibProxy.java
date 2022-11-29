package org.example.compare;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理类
 */
public class CGLibProxy implements MethodInterceptor {
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
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("jdk proxy before code");
        Object result = method.invoke(targetObject, args);
        System.out.println("jdk proxy after code");
        return result;
    }
}
