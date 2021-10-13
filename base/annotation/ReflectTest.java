package annotation;

/*
框架类
 */

import java.lang.reflect.Method;

@Pro(className = "annotation.Demo1", methodName = "show")
public class ReflectTest {

    //可以创建任意类的对象，可以执行任意方法,前提"不能改变该类的任何代码，可以创建任意类的对象，执行任意方法"
    public static void main(String[] args) throws Exception {

        //解析注解
        //获取该类的字节码文件对象
        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        //获取上边的注解对象
        //内存中生成了一个该注解接口的子类对象
        Pro annotation = reflectTestClass.getAnnotation(Pro.class);
        //条用注解对象中定义的抽象方法，获取返回值
        String s = annotation.className();
        String s1 = annotation.methodName();
        System.out.println(s);
        System.out.println(s1);

        Class<?> aClass = Class.forName(s);
        Object o = aClass.newInstance();
        Method method = aClass.getMethod(s1);
        method.invoke(o);

    }
}
