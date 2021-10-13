package reflect;

/*
框架类
 */

import domain.Person;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {

    //可以创建任意类的对象，可以执行任意方法,前提"不能改变该类的任何代码，可以创建任意类的对象，执行任意方法"
    public static void main(String[] args) throws Exception {
         //加载配置文件，转换成一个集合
        Properties pro = new Properties();
        ClassLoader classLoader = ReflectTest.class.getClassLoader(); //类加载器
//        classLoader.getResource();//获取资源路径
        InputStream resourceAsStream = classLoader.getResourceAsStream("pro.properties");//获取资源对应的字节流
        pro.load(resourceAsStream);

        //获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //加载该类内存
        Class cls = Class.forName(className);
        //创建对象
        Object obj = cls.newInstance();
        //获取方法对象
        Method method = cls.getMethod(methodName);
        //执行方法
        method.invoke(obj);
    }
}
