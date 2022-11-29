public class LambdaTest {
    public static void main(String[] args) {
        createThread();
    }

    private static void createThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("createThread run");
            }
        });
        Thread thread = new Thread(() -> System.out.println("createThread Lambda"));
        thread.start();
    }
}
