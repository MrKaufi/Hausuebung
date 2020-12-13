/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twelsch.htl.pos3.demo.java8.unittesting;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Torsten Welsch
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of foo method, of class Main.
     */
    @Test
    public void testFoo() {
        System.out.println("foo");
        int i = 1;
        int expResult = -1;
        int result = Main.foo(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of fooNonStatic method, of class Main.
     */
    @Test
    public void testFooNonStatic() {
        System.out.println("fooNonStatic");
        int i = 1;
        Main instance = new Main();
        int expResult = 4711;
        int result = instance.fooNonStatic(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of bar method, of class Main.
     */
    @Test
    public void testBar() {
        System.out.println("bar");
        int i = 11;
        Main instance = new Main();
        instance.bar(i);
        int result = instance.getI();
        int expResult = i;
        assertTrue(result == expResult);
    }

    /**
     * Test of getNow method, of class Main.
     */
    @Test
    public void testGetNow() {
        System.out.println("getNow");
        Main instance = new Main();
        Date beforeResult = new Date();
        Date result = instance.getNow();
        Date afterResult = new Date();
        
//        System.out.println(beforeResult.getTime());
//        System.out.println(afterResult.getTime());
//        System.out.println(result.getTime());
        
        assertTrue((
                beforeResult.before(result) || 
                beforeResult.equals(result))
                &&
                (afterResult.after(result) ||
                afterResult.equals(result)));
    }

    
}
