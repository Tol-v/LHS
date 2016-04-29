import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;


public class PredicateTest {
     static ArrayList<String> args_empty = new ArrayList<String>();
    static Predicate pr1;

    static ArrayList<String> args_2_fill = new ArrayList<String>();
    static Predicate pr2;

    static ArrayList<String> args_3_fill = new ArrayList<String>();
    static Predicate pr3;

    static ArrayList<String> args_4_fill = new ArrayList<String>();
    static Predicate pr4;

    static ArrayList<String> args_5_fill = new ArrayList<String>();
    static Predicate pr5;


    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        args_2_fill.add("123");
        args_2_fill.add("321");

        args_3_fill.add("Abc");
        args_3_fill.add("a");

        args_4_fill.add("abc");
        args_4_fill.add("b");

        args_4_fill.add("Za");
        args_4_fill.add("Zxc");

        pr1 = new Predicate("test1", new ArrayList<String>());//null
        pr2 = new Predicate("test2", args_2_fill);            //123 321
        pr3 = new Predicate("test3", args_3_fill);            //Abc a
        pr4 = new Predicate("test4", args_4_fill);            //abc b
        pr5 = new Predicate("test5", args_5_fill);            //Za Zxc


    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }


    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testGetArgs() throws Exception {

        //empty test
        Assert.assertEquals(args_empty, pr1.getArgs());

        //2 item test
        Assert.assertEquals(args_2_fill, pr2.getArgs());

    }

    @Test
    public void testGetNumArgs() throws Exception {
        Assert.assertEquals(0, pr1.getNumArgs());

        Assert.assertEquals(2, pr2.getNumArgs());
    }

    @Test
    public void testHasVariables() throws Exception {
        Assert.assertFalse(pr1.hasVariables());
        Assert.assertFalse(pr2.hasVariables());
        Assert.assertTrue(pr3.hasVariables());
        Assert.assertTrue(pr4.hasVariables());
        Assert.assertFalse(pr5.hasVariables());
    }

    @Test
    public void testGetVarIndex() throws Exception {

        Assert.assertEquals(-1, pr1.getVarIndex());
        Assert.assertEquals(-1, pr2.getVarIndex());
        Assert.assertEquals(1, pr3.getVarIndex());
        Assert.assertEquals(1, pr4.getVarIndex());
        Assert.assertEquals(-1, pr5.getVarIndex());
    }

    @Test
    public void testEquals() throws Exception {
         ArrayList<String> arg = new ArrayList<String>();
         arg.add("123");
         arg.add("321");
         Predicate p = new Predicate("test_r", arg);
         Assert.assertFalse(pr1.equals(pr2));
         Assert.assertTrue(pr2.equals(p));

    }



}