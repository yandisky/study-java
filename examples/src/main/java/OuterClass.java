public class OuterClass {
    private int noStatic = 0;
    private static int isStatic = 0;

    class NoStaticClass {
        private int noStatic = 1;

        public void test() {
            System.out.println(noStatic);
            System.out.println(isStatic);
        }
    }

    static class IsStaticClass {
        private static int isStatic = 1;

        public void test() {
            System.out.println(isStatic);
        }
    }

    public static void main(String[] args) {
        NoStaticClass noStaticClass = new OuterClass().new NoStaticClass();
        noStaticClass.test();
        IsStaticClass isStaticClass = new IsStaticClass();
        isStaticClass.test();
    }
}
