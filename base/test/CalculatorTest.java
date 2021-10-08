package test;

import junit.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class CalculatorTest {
    /**
     * 初始执行，一次，方法名固定为init
     */
    @Before
    public void init(){
        System.out.println("init...");
    }

    @Test
    public void testAdd(){
        Calculator c = new Calculator();
        int result = c.add(1,2);
        System.out.println(result);
        Assert.assertEquals(3, result);
    }

    /**
     *最后执行，一次，方法名固定close
     */
    @After
    public void close(){
        System.out.println("close");
    }
}
