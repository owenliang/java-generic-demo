package cc.yuerblog;

import java.util.List;

public class Main {
    // 多态1
    private static void demo1() {
        Student a = new Student();
        Person b = a;
    }
    // 多态2
    private static void demo2() {
        Student[] a = new Student[5];
        Person[] b = a;
    }
    // 泛型1
    private static void demo3() {
        List<Student> a = null;
        List<Person> b = a; // 报错，List<A>和List<B>不受A,B多态影响
    }
    // 泛型2
    private static void demo4() {
        List<Student> a = null;
        List<?> b = a;
        b.add(new Student()); // 报错，我们并不知道?是什么类型，所以不能添加任何元素

        Object o = b.get(0);    // 无论?是什么类型，都是Object的子类
    }
    // 泛型3
    private static void demo5() {
        List<Student> a = null;
        List<? extends Person> b = a;   // ?通配Person的所有子类
        b.add(new Student()); // 报错，我们并不知道?到底是Person的哪个子类
        b.add(new Person());    // 报错，?是Person的某个子类，我们并不知道具体是哪种类型

        Person o = b.get(0);    // 无论?是什么类型，都是Person的子类
    }
    // 泛型4
    private static void demo6() {
        List<Person> a = null;
        List<? super Student> b = a;    // ?通配Student的所有父类

        b.add(new Student());   // ?一定是Student的父类，所以add Student符合多态
        b.add(new ClassLeader());   // ?一定是Student的父类，也一定是ClassLeader的父类（因为ClassLeader继承Student），符合多态
        b.add(new Person());    // 报错，?虽然是Student的父类，但并不知道具体是哪个父类，所以无法add父类对象
    }
    public static void main(String[] args) {
    }
}
