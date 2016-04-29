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


    public ArrayList<Rule> fetchRules(Predicate goal) {
        ArrayList<Rule> rules = new ArrayList<Rule>();

        for(Rule currRule : this.rules) {
            String rhsName = currRule.getRHS().getName();

            if(rhsName.equals(goal.getName())) {
                if(nonVarArgsMatch(goal, currRule.getRHS()))
                    rules.add(currRule);
            }
        }

        return rules;
    }
}
