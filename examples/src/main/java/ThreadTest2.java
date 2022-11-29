public class ThreadTest2 {
    public static void main(String[] args) {
        //yield： yield只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
        //sleep：sleep使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；
        YieldTest yieldTest1 = new YieldTest("线程1");
        YieldTest yieldTest2 = new YieldTest("线程2");
        new Thread(yieldTest1).start();
        new Thread(yieldTest2).start();
    }
}

class YieldTest implements Runnable {
    private String name;

    public YieldTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " 线程运行开始!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("" + this.name + "-----" + i);
            if (i == 3) {
                Thread.yield();
            }
        }
        System.out.println(this.name + " 线程运行结束!");
    }
}
