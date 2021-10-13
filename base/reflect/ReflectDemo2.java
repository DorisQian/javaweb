package reflect;

import domain.Person;
import javafx.scene.input.InputMethodTextRun;

import java.lang.reflect.Field;

public class ReflectDemo2 {
    /*
        * Class对象功能：
		* 获取功能：
			1. 获取成员变量们
				* Field[] getFields() ：获取所有public修饰的成员变量
				* Field getField(String name)   获取指定名称的 public修饰的成员变量

				* Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
				* Field getDeclaredField(String name)
			2. 获取构造方法们
				* Constructor<?>[] getConstructors()
				* Constructor<T> getConstructor(类<?>... parameterTypes)

				* Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
				* Constructor<?>[] getDeclaredConstructors()
			3. 获取成员方法们：
				* Method[] getMethods()
				* Method getMethod(String name, 类<?>... parameterTypes)

				* Method[] getDeclaredMethods()
				* Method getDeclaredMethod(String name, 类<?>... parameterTypes)

			4. 获取全类名
				* String getName()
     */

    public static void main(String[] args) throws Exception {
        // 1、获取person的class对象
        Class personClass = Person.class;

        // 1、Field[] getFields() ：获取所有public修饰的成员变量
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("---------------------");
        Field a = personClass.getField("a");
        //获取成员变量a的值
        Person p = new Person();
        Object o = a.get(p);
        System.out.println(o);
        //设置a的值
        a.set(p,"张三");
        System.out.println(p);

        System.out.println("=======================");

        // Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        Field d = personClass.getDeclaredField("d");
        /*
            使用私有变量，需要先忽略访问权限修饰符的安全检查，不然有异常，
            java.lang.IllegalAccessException:
            Class reflect.ReflectDemo2 can not access a member of class domain.Person with modifiers "private"
         */
        d.setAccessible(true); //暴力反射
        Object o1 = d.get(p);
        System.out.println(o1);
    }

}
