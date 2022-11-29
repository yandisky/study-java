package org.example.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class MyProxyTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 第一种
        // 1,生成$Proxy0的class文件
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 2,获取动态代理类
        Class proxyClass = Proxy.getProxyClass(IHello.class.getClassLoader(), IHello.class);
        // 3,获取代理类的构造函数，传入参数类型InvocationHandler.class
        Constructor proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);
        // 4,通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IHello iHelloObject1 = (IHello) proxyClassConstructor.newInstance(new MyInvocationHandler(new HelloImpl()));
        // 5,通过代理对象调用目标方法
        iHelloObject1.sayHello();

        // 第二种
        // 1,获取动态代理类（newProxyInstance将2~4步骤封装）
        IHello iHelloObject2 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),// 加载接口的类加载器
                new Class[]{IHello.class},// 一组接口
                new MyInvocationHandler(new HelloImpl()));// 自定义的InvocationHandler
        // 2,通过代理对象调用目标方法
        iHelloObject2.sayHello();

        // 验证非接口方法,代理对象无法使用
        HelloImpl iHelloObject3 = (HelloImpl) Proxy.newProxyInstance(IHello.class.getClassLoader(),// 加载接口的类加载器
                new Class[]{IHello.class},// 一组接口
                new MyInvocationHandler(new HelloImpl()));// 自定义的InvocationHandler
        iHelloObject3.sayOthers();
    }
}
