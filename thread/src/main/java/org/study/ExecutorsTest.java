package org.study;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 调试代码
 * 问题：一个线程池中的线程异常了，那么线程池会怎么处理这个线程?
 * 原文：https://mp.weixin.qq.com/s/QTZ8_1ElOl2Cjx4-9Pc9Uw
 */
public class ExecutorsTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTaskExecutor executorService = buildThreadPoolTaskExecutor();
        //执行方式为execute，异常的输出情况
        executorService.execute(() -> sayHi("execute"));
        //在java.util.concurrent.ThreadPoolExecutor#runWorker中抛出了异常
        //在java.lang.ThreadGroup#uncaughtException进行了异常处理
        //execute指定异常处理方式
        //1，new Thread()
        /*Thread t = new Thread();
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
            }
        });*/
        //2，线程池
        /*ExecutorService threadPool = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((t1, e) -> System.out.println(e.getMessage()));
            return t;
        });*/
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println("===============================");
        //执行方式为submit，异常的输出情况
        Future<?> future = executorService.submit(() -> sayHi("submit"));
        //submit方法将task封装成FutureTask，再将FutureTask传给execute进行调用，返回FutureTask
        //在java.util.concurrent.ThreadPoolExecutor#runWorker中的task.run()
        //在java.util.concurrent.FutureTask#run中的setException(ex)只是记录异常，实际调用Future.get()方法时，才抛出异常
        //在java.util.concurrent.FutureTask#get中的report(s)抛出异常
        try {
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //线程异常是否会放回线程池？
        //在java.util.concurrent.ThreadPoolExecutor#runWorker中的processWorkerExit(w, completedAbruptly)会移除异常线程
    }

    private static void sayHi(String name) {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name + "】";
        System.out.println(printStr);
        throw new RuntimeException(printStr + ",throw exception");
    }

    private static ThreadPoolTaskExecutor buildThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setThreadNamePrefix("Thread");
        executorService.setCorePoolSize(5);
        executorService.setMaxPoolSize(10);
        executorService.setQueueCapacity(1000);
        executorService.setKeepAliveSeconds(30);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.initialize();
        return executorService;
    }
}
