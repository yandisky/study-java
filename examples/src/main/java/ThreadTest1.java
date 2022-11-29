import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest1 {
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();

        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest);
        thread.start();

        CallableTest callableTest = new CallableTest();
        FutureTask<Integer> ft = new FutureTask<Integer>(callableTest);
        Thread thread1 = new Thread(ft);
        thread1.start();
        try {
            System.out.println("返回值:" + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println("这是一个Thread的线程!");
    }
}

class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("这是一个Runnable的线程!");
    }
}

class CallableTest implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("这是一个Callable的线程!");
        return 2;
    }
}
