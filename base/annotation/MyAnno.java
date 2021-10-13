package annotation;


public @interface MyAnno {

    public abstract String show1();
    int age();
    String name() default "张三";

    Person per();
    MyAnno2 anno2();

    String[] strs();
    /**
     * 如果只有一个属性需要赋值，并且属性的名称是value，使用时则value可以省略，直接定义值即可，即@MyAnno(12) //int value()。
     */
}
