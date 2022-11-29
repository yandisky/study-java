public class UnsafeTest2 {
    private static volatile int count;

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            int start = 0;
            while (start++ < 100000) {
                count++;
            }
            System.out.println("ThreadA: " + count);
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            int start = 0;
            while (start++ < 100000) {
                count++;
            }
            System.out.println("ThreadB: " + count);
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
}
