import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;


public class ParserTest {

    @BeforeClass
    public static void setUpBeforClass() throws Exception {

}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }


    @Test
    public void testHasLHS() throws Exception {
        Assert.assertTrue(Parser.hasLHS("a=>b"));
        Assert.assertFalse(Parser.hasLHS("a=>=>b"));
        Assert.assertFalse(Parser.hasLHS("a<=b"));
    }

    @Test
    public void testGetLHS() throws Exception {
        String str = "LostWeight(y)&HasSymptom(y,Cough)=>Diagnosis(John,Uninfected)";
        String answer = "LostWeight(y)&HasSymptom(y,Cough)";
        Assert.assertEquals(answer, Parser.getLHS(str));
    }

    @Test
    public void testGetRHS() throws Exception {
        String str = "LostWeight(y)&HasSymptom(y,Cough)=>Diagnosis(John,Uninfected)";
        String answer = "Diagnosis(John,Uninfected)";
        Assert.assertEquals(answer, Parser.getRHS(str));
    }

    @Test
    public void testSeparateLHS() throws Exception {
        ArrayList<String> answer = new ArrayList<String>();
        String str = "a(b)&b(c,d)&a(d)";
        answer.add("a(b)");
        answer.add("b(c,d)");
        answer.add("a(d)");
        Assert.assertEquals(answer, Parser.separateLHS(str));
    }

    @Test
    public void testReadPredicate() throws Exception {
        /*
        Predicate answer1 = new Predicate("name1", null);
        Assert.assertEquals(answer1, Parser.readPredicate("name1()"));
        */

        ArrayList<String> arr2 = new ArrayList<String>();
        arr2.add("b");
        Predicate answer2 = new Predicate("name2", arr2);
        Assert.assertTrue(answer2.equals(Parser.readPredicate("name2(b)")));

        ArrayList<String> arr3 = new ArrayList<String>();
        arr3.add("b");
        arr3.add("c");
        Predicate answer3 = new Predicate("name3", arr3);
        Assert.assertTrue(answer3.equals(Parser.readPredicate("name2(b,c)")));

        ArrayList<String> arr4 = new ArrayList<String>();
        arr4.add("b");
        arr4.add("c");
        arr4.add("d");
        Predicate answer4 = new Predicate("name3", arr3);
        Assert.assertTrue(answer4.equals(Parser.readPredicate("name4(b,c,d)")));
    }

    @Test
    public void testPrintOutput() throws Exception {

    }

}