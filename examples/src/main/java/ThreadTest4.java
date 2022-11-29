import java.util.concurrent.locks.LockSupport;

public class ThreadTest4 {
    static Thread t1, t2;

    public static void main(String[] args) {
        char[] c1 = "123456789".toCharArray();
        char[] c2 = "ABCDEFGHI".toCharArray();

        t1 = new Thread(() -> {
            for (char c : c1) {
                System.out.println(Thread.currentThread().getName() + ":" + c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            LockSupport.unpark(t2);
        }, "T1");

        t2 = new Thread(() -> {
            for (char c : c2) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + ":" + c);
                LockSupport.unpark(t1);
            }
            LockSupport.unpark(t1);
        }, "T2");

        t1.start();
        t2.start();
    }
}
