import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class KnowledgeBaseTest {
        static ArrayList<String> args_empty = new ArrayList<String>();
    static ArrayList<String> args2 = new ArrayList<String>();
    static ArrayList<String> args3 = new ArrayList<String>();

    static Predicate query;
    static Predicate pr1;
    static Predicate pr2;
    static Predicate pr3;

    static Rule rule1;
    static Rule rule2;
    static Rule rule3;
    static KnowledgeBase kb1;

    @BeforeClass
    public static void setUpBeforClass() throws Exception {
        args2.add("Abc");
        args2.add("a");

        args3.add("a1");
        args3.add("a2");
        args3.add("a3");

        query = new Predicate("q", args_empty);

        pr1 = new Predicate("q", args_empty);
        pr2 = new Predicate("p2", args2);
        pr3 = new Predicate("p3", args3);

        rule1 = new Rule(query, null);

        ArrayList<Predicate> buf = new ArrayList<Predicate>();
        buf.add(pr2);
        buf.add(pr3);
        rule2= new Rule(query, buf);
        rule3 = new Rule(pr3, null);

        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        kb1 = new KnowledgeBase(query, rules);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }



    @Test
    public void testFetchRules() throws Exception {

        ArrayList<Rule> answer = new ArrayList<Rule>();
        answer.add(rule1);
        answer.add(rule2);

        Assert.assertEquals(answer, kb1.fetchRules(query));
    }

}