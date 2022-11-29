import java.lang.reflect.Constructor;

class SingletonTest {
    public static void main(String[] args) throws Exception {
        //验证例子4的反射攻击
        SingletonTest4 singleton = SingletonTest4.getInstance();
        Constructor<SingletonTest4> constructor = SingletonTest4.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonTest4 newSingleton = constructor.newInstance();
        System.out.println(singleton == newSingleton);
    }
}

//例如1：饿汉式
class SingletonTest1 {

    private SingletonTest1() {
    }

    private static SingletonTest1 instance = new SingletonTest1();

    public static SingletonTest1 getInstance() {
        return instance;
    }
}

//例如2：饱汉式（懒汉式）
//有线程安全问题
class SingletonTest2 {

    private SingletonTest2() {
    }

    private static SingletonTest2 instance;

    public static SingletonTest2 getInstance() {
        if (instance == null) {
            instance = new SingletonTest2();
        }
        return instance;
    }
}

//例如3：双重锁检查
//这里采用了双重校验的方式，对懒汉式单例模式做了线程安全处理。通过加锁，可以保证同时只有一个线程走到第二个判空代码中去，这样保证了只创建一个实例。
//这里还用到了volatile关键字来修饰singleton，其最关键的作用是防止指令重排，防止其他线程返回未初始化完全的instance。
class SingletonTest3 {
    private SingletonTest3() {
    }

    private static volatile SingletonTest3 instance;

    public static SingletonTest3 getIstance() {
        if (instance == null) {
            synchronized (SingletonTest3.class) {
                if (instance == null) {
                    instance = new SingletonTest3();
                }
            }
        }
        return instance;
    }
}

//例如4：静态内部类
//通过静态内部类的方式实现单例模式是线程安全的，同时静态内部类不会在SingletonTest4类加载时就加载，而是在调用getInstance()方法时才进行加载，达到了懒加载的效果。
//似乎静态内部类看起来已经是最完美的方法了，其实不是，可能还存在反射攻击或者反序列化攻击
class SingletonTest4 {
    private SingletonTest4() {
    }

    private static class SingletonTest44 {
        private static SingletonTest4 instance = new SingletonTest4();
    }

    public static SingletonTest4 getInstance() {
        return SingletonTest44.instance;
    }
}

//例如5：枚举单例(枚举需要在JDK1.5之后的版本)，最好的解决方法
class ClassFactory {
    private enum MyEnumSingleton {
        singletonFactory;
        private SingletonTest5 instance;

        MyEnumSingleton() {//枚举类的构造方法在类加载是被实例化
            instance = new SingletonTest5();
        }

        public SingletonTest5 getInstance() {
            return instance;
        }
    }

    public static SingletonTest5 getInstance() {
        return MyEnumSingleton.singletonFactory.getInstance();
    }
}

class SingletonTest5 {
    public SingletonTest5() {
    }
}