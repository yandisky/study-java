public class AmountTest {
    public static void main(String[] args) {
        String string1 = "200";

        Integer integer1 = 200;
        Integer integer2 = 200;
        System.out.println(integer1 == 200);
        System.out.println(integer1 == integer2);
        System.out.println(integer1.equals(integer2));
        System.out.println(integer1.equals(string1));
        System.out.println("=======================");
        Double double1 = 200d;
        Double double2 = 200d;
        System.out.println(double1 == 200d);
        System.out.println(double1 == double2);
        System.out.println(double1.equals(double2));
        System.out.println(double1.equals(string1));
        System.out.println("=======================");
        Float f = 12312.12f;
        System.out.println(f.doubleValue());
        System.out.println(Double.parseDouble(f.toString()));
    }
}
