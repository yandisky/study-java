public class ObjectClass {
    public static void main(String[] args) {
        Father person = new Person("1");
        //System.out.println(person.getPersonName());
        Father person2 = new Person("2");
        System.out.println(person == person2);
        System.out.println(person.equals(person2));
    }
}

class Person extends Father {
    public Person(String name) {
        super(name);
    }

    public String getPersonName() {
        return "A-" + getName();
    }
}

class Father {
    private String name;

    public Father(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student {
    private int id;
    String name;
    protected boolean sex;
    public float score;
}

class Get {
    //获取反射机制三种方式
    public static void main(String[] args) throws ClassNotFoundException {
        //方式一(通过建立对象)
        Student stu = new Student();
        Class classobj1 = stu.getClass();
        System.out.println(classobj1.getName());
        //方式二（所在通过路径-相对路径）
        Class classobj2 = Class.forName("Student");
        System.out.println(classobj2.getName());
        //方式三（通过类名）
        Class classobj3 = Student.class;
        System.out.println(classobj3.getName());
    }
}