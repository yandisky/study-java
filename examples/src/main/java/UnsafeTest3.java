import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeTest3 {
    private static ConcurrentHashMap<String, Integer> concurrentHashMap1 = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, AtomicInteger> concurrentHashMap2 = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // 验证ConcurrentHashMap是否线程安全
        concurrentHashMap1.put("test", 1);
        concurrentHashMap2.put("test", new AtomicInteger(0));
        Thread t1 = new Thread(() -> {
            int i = 1;
            while (i <= 1000) {
                concurrentHashMap1.put("test", concurrentHashMap1.get("test") + 1);
                concurrentHashMap2.get("test").incrementAndGet();
                i++;
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            int i = 1;
            while (i <= 1000) {
                concurrentHashMap1.put("test", concurrentHashMap1.get("test") + 1);
                concurrentHashMap2.get("test").incrementAndGet();
                i++;
            }
        }, "T2");

        t1.start();
        t2.start();

        while (true) {
            if (t1.getState() == Thread.State.TERMINATED && t2.getState() == Thread.State.TERMINATED) {
                System.out.println(concurrentHashMap1.get("test"));
                System.out.println(concurrentHashMap2.get("test"));
                break;
            }
        }
    }
}
