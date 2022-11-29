public class UnsafeTest1 {
    private long count;

    public void counter() {
        int start = 0;
        while (start++ < 10000) {
            count++;
        }
    }

    public long getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeTest1 unsafeTest = new UnsafeTest1();
        Thread thread1 = new Thread(unsafeTest::counter, "线程1");
        Thread thread2 = new Thread(unsafeTest::counter, "线程2");

        thread1.start();
        //thread1.join();

        thread2.start();
        thread2.join();

        System.out.println(unsafeTest.getCount());
    }
}
