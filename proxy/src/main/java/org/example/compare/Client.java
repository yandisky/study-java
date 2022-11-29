package org.example.compare;

public class Client {
    public static void main(String[] args) {
        System.out.println("JDK");
        JDKProxy jdkProxy = new JDKProxy();
        IHello iHello1 = (IHello) jdkProxy.newProxy(new HelloImpl());
        iHello1.sayHello();

        System.out.println("CGLIB");
        CGLibProxy cgLibProxy = new CGLibProxy();
        IHello iHello2 = (IHello) cgLibProxy.newProxy(new HelloImpl());
        iHello2.sayHello();
    }
}
