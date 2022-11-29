public class UnsafeTest4 {
    private static long count1;
    private static volatile long count2;
    private static int count3;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            count1 = 1L;
            count2 = 1L;
            count3 = 1;
            System.out.println("ThreadA 1: " + count1);
            System.out.println("ThreadA 2: " + count2);
            System.out.println("ThreadA 3: " + count3);
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            count1 = 2L;
            count2 = 2L;
            count3 = 2;
            System.out.println("ThreadB 1: " + count1);
            System.out.println("ThreadB 2: " + count2);
            System.out.println("ThreadB 3: " + count3);
        }
    }

    public static void main(String[] args) {
        // 32位的jvm环境下long，double的赋值是非原子性操作
        System.out.println(System.getProperty("sun.arch.data.model"));
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
}
