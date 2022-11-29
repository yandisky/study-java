import java.util.*;

public class ListMapSetTest {
    public static void main(String[] args) {
        testTreeMap();
    }

    public static void testArrayList() {
        System.out.println("ArrayList");
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        //普通的for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //增强for循环(jdk1.5之后出现)
        for (String str : list) {
            System.out.println(str);
        }
        //Iterator(迭代器)
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //list拉姆达表达式遍历
        list.forEach(v -> {
            System.out.println(v);
        });
        //list双冒号运算符遍历
        list.forEach(System.out::println);
    }

    public static void testHashSet() {
        System.out.println("HashSet");
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        for (String str : set) {
            System.out.println(str);
        }
        Iterator<String> iterator1 = set.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    public static void testHashMap() {
        System.out.println("HashMap");
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        //通过Map.keySet遍历
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }
        //通过Map.entrySet使用iterator遍历
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //通过Map.entrySet进行遍历
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //map拉姆达表达式遍历
        map.forEach((k, v) -> {
            System.out.println("k=" + k + "，v=" + v);
        });
    }

    public static void testTreeMap() {
        System.out.println("TreeMap");
        Map<String, String> map = new TreeMap<>();
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");
        map.put("4", "44");
        map.put("5", "55");
        System.out.println(map.get("1"));
    }
}
