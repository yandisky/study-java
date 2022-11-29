import java.util.Random;

public class ThreadTest3 {
    public static void main(String[] args) {
        //使用join方法指等待某个线程终止。也就是说当子线程调用了join方法之后，后面的代码只有等待该线程执行完毕之后才会执行。
        System.out.println(Thread.currentThread().getName() + "主线程开始运行!");
        joinTest t1 = new joinTest("A");
        joinTest t2 = new joinTest("B");
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "主线程运行结束!");
    }
}

class joinTest extends Thread {
    public joinTest(String name) {
        super(name);
    }

    public void run() {
        System.out.println(this.getName() + " 线程运行开始!");
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程" + this.getName() + "运行 : " + i);
            try {
                sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + " 线程运行结束!");
    }
}
