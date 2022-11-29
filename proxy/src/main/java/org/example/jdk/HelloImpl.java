package org.example.jdk;

public class HelloImpl implements IHello {
    public HelloImpl() {
        System.out.println("hello构造");
    }

    // 非接口方法,代理对象无法使用
    public void sayOthers() {
        System.out.println("say others");
    }

    public void sayHello() {
        System.out.println("Hello World!");
    }
}
