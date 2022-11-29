import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        test5();
    }

    private static void test1() {
        List<String> list = Arrays.asList("张三", "李四", "王五");
        System.out.println("过滤之前:" + list);
        List<String> result = new ArrayList<>();
        for (String str : list) {
            if (!"李四".equals(str)) {
                result.add(str);
            }
        }
        System.out.println("过滤之后:" + result);
        List<String> result2 = list.stream().filter(str -> !"李四".equals(str)).collect(Collectors.toList());
        System.out.println("stream 过滤之后:" + result2);
    }

    private static void test2() {
        //构造Stream流
        Stream stream = Stream.of("a", "b", "c");

        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();
    }

    private static void test3() {
        //一个Stream流只可以使用一次，下面代码需要注释
        Stream<String> stream2 = Stream.of("a", "b", "c");
        //转换成 Array
        String[] strArray1 = stream2.toArray(String[]::new);
        //转换成 Collection
        List<String> list1 = stream2.collect(Collectors.toList());
        List<String> list2 = stream2.collect(Collectors.toCollection(ArrayList::new));
        Set set1 = stream2.collect(Collectors.toSet());
        Stack stack1 = stream2.collect(Collectors.toCollection(Stack::new));
        //转换成 String
        String str = stream2.collect(Collectors.joining()).toString();
    }

    private static void test4() {
        //map方法用于映射每个元素到对应的结果，一对一。
        //转换大写
        List<String> list3 = Arrays.asList("zhangSan", "liSi", "wangWu");
        System.out.println("转换之前的数据:" + list3);
        List<String> list4 = list3.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list4);
        //转换数据类型
        List<String> list31 = Arrays.asList("1", "2", "3");
        System.out.println("转换之前的数据:" + list31);
        List<Integer> list41 = list31.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list41);
        //获取平方
        List<Integer> list5 = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        List<Integer> list6 = list5.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println("平方的数据:" + list6);
    }

    private static void test5() {
        //filter方法用于通过设置的条件过滤出元素。
        //通过与 findAny 得到 if/else 的值
        List<String> list = Arrays.asList("张三", "李四", "王五", "xuwujing");
        String result3 = list.stream().filter(str -> "李四".equals(str)).findAny().orElse("找不到!");
        String result4 = list.stream().filter(str -> "李二".equals(str)).findAny().orElse("找不到!");
        System.out.println("stream 过滤之后 2:" + result3);
        System.out.println("stream 过滤之后 3:" + result4);
        //通过与 mapToInt 计算和
        List<User> lists = new ArrayList<User>();
        lists.add(new User(6, "张三"));
        lists.add(new User(2, "李四"));
        lists.add(new User(3, "王五"));
        lists.add(new User(1, "张三"));
        int sum = lists.stream().filter(u -> "张三".equals(u.getName())).mapToInt(u -> u.getId()).sum();
        System.out.println("计算结果:" + sum);
        //flatMap 方法用于映射每个元素到对应的结果，一对多。
        String worlds = "The way of the future";
        List<String> list7 = new ArrayList<>();
        list7.add(worlds);
        List<String> list8 = list7.stream().flatMap(str -> Stream.of(str.split(" ")))
                .filter(world -> world.length() > 0).collect(Collectors.toList());
        System.out.println("单词:");
        list8.forEach(System.out::println);
    }
}

class User {
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
