package annotation;

//单独copy出来 去掉package，javadoc 类名，生成文档html文件

/**
 * 注解javadoc演示
 *
 * @author doris
 * @version 1.0
 * @since 1.5
 */
public class AnnoDemo1 {
    /**
     * 计算两数的和
     *
     * @param a 整数
     * @param b 整数
     * @return 两数之和
     */
    public int add(int a, int b) {
        return a + b;
    }

}
