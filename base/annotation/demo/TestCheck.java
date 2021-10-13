package annotation.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 简单的测试框架
 * 主方法执行后，会自动执行被检测的所有方法，判断是否有异常，记录到文件中
 */
public class TestCheck {
    public static void main(String[] args) throws IOException {
        //创建计算器对象
        Calculator calculator = new Calculator();
        //获取字节码文件对象
        Class<? extends Calculator> aClass = calculator.getClass();
        //获取所有方法
        Method[] methods = aClass.getMethods();
        int num = 0; //出现异常的次数
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("bug.txt"));

        for (Method method : methods) {
            //判断方法上是否有check注解
            if(method.isAnnotationPresent(Check.class)){
                try {
                    //有，则执行
                    method.invoke(calculator);
                } catch (Exception e) {
                    //捕获异常
                    //记录到文件中
                    num++;
                    bufferedWriter.write(method.getName() + "方法出异常了");
                    bufferedWriter.newLine();
                    bufferedWriter.write("异常的名称:" + e.getCause().getClass().getSimpleName());
                    bufferedWriter.newLine();
                    bufferedWriter.write("异常的原因" + e.getCause().getMessage());
                    bufferedWriter.newLine();
                    bufferedWriter.write("--------------------");
                    bufferedWriter.newLine();
                }
            }
        }

        bufferedWriter.write("本次测试一共出现 "+num+" 次异常");
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
