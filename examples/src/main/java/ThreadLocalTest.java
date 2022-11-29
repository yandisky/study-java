public class ThreadLocalTest {
    private static ThreadLocal<String> local1 = new ThreadLocal<>();
    private static ThreadLocal<Integer> local2 = new ThreadLocal<>();

    public static void main(String[] args) {
        local1.set("test");
        local2.set(1);
        System.out.println(local2.get());
        System.out.println(local1.get());
    }
}
