import java.util.ArrayList;

public class KnowledgeBase {
    private Predicate query;
    private ArrayList<Rule> rules;

    public KnowledgeBase(Predicate query, ArrayList<Rule> rules) {
        this.query = query;
        this.rules = rules;
    }

    public Predicate getQuery() {
        return this.query;
    }
}
