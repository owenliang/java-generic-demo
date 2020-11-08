package cc.yuerblog;

import java.util.List;

public class Main {
    // 多态1
    private static void demo1() {
        Student a = new Student();

        // 正确
        Person b = a;
    }
    // 多态2
    private static void demo2() {
        Student[] a = new Student[5];

        // 正确
        Person[] b = a;
    }
    // 泛型1
    private static void demo3() {
        List<Student> a = null;
        List<Person> b = a; // 错误：List<Student>和List<Person>是两个不同的类型，不受Student与Person本身多态影响
    }
    // 泛型2
    private static void demo4() {
        List<Student> a = null;
        List<?> b = a;

        // 错误：编译器不知道?原本是什么类型，所以添加任何元素都是禁止的
        b.add(new Student());
        b.add(new Person());
        b.add(new Object());

        // 正确，无论?原本是什么类型，Object都是基类，符合多态向上转换
        Object o = b.get(0);
    }
    // 泛型3
    private static void demo5() {
        List<Student> a = null;

        // 正确：?通配任意的Person子类
        List<? extends Person> b = a;

        // 报错：虽然?是Person的某个子类，但是我们并不知道List中的真实数据类型，因此禁止添加元素
        b.add(new Student());
        b.add(new Person());

        // 正确：无论?是Person的哪个子类，它们都以Person为基类
        Person o = b.get(0);
    }
    // 泛型4
    private static void demo6() {
        List<Person> a = null;

        // 正确：?通配Student的任意父类
        List<? super Student> b = a;

        // 正确：?通配任意Student父类，因此Student和ClassLeader都符合多态向上转换
        b.add(new Student());
        b.add(new ClassLeader());

        // 错误：?通配Student父类，但Student父类不一定只有Person，所以<? super>没法add父类的实例
        b.add(new Person());

        // 正确：?通配Student任意父类，但是?也不知道具体是哪个，所以只能多态向上为Object
        Object o = b.get(0);
    }
    public static void main(String[] args) {
        // 总结一下:
        // 1,<? extends A> 通配A的子类，禁止任意add，可以get为A类
        // 2,<? super A> 通配A的父类，可以add任意A的子类对象，可以get为Object
    }
}
