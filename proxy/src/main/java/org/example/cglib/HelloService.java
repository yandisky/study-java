package org.example.cglib;

public class HelloService {
    public HelloService() {
        System.out.println("hello构造");
    }

    //该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
    final public void sayOthers() {
        System.out.println("say others");
    }

    public void sayHello() {
        System.out.println("say hello");
    }
}
