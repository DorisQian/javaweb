package reflect;

import domain.Person;

public class ReflectDemo1 {
    /*
    获取class的三种方式
        1、Class.forName("全类名");将字节码文件加载进内存，返回class对象
            多用于配置文件，将类名定义在配置文件中，读取文件加载类
        2、类名.class；通过类名的属性class获取
            多用于参数的传递
        3、对象.getClass(); getClass()方法在Object类中定义着
            多用于对象的获取字节码的方式

    结论：
        同一个字节码文件（*.class）在一次程序的运行过程中，只会被加载一次，无论怎样获取的class对象，都是同一个
     */

    public static void main(String[] args) throws Exception {

        //1.Class.forName("全类名")
        Class cls1 = Class.forName("domain.Person");
        System.out.println(cls1);

        //2、类名.class；通过类名的属性class获取
        Class cls2 = Person.class;
        System.out.println(cls2);

        //3、对象.getClass(); getClass()方法在Object类中定义着
        Person p = new Person();
        Class cls3 = p.getClass();
        System.out.println(cls3);

        // == 比较三个对象
        System.out.println(cls1 == cls2);
        System.out.println(cls1 == cls3);

        Class c = Class.forName("domain.Student");
        System.out.println(cls1 == c);
    }
}
