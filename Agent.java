import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Agent {
    private final static String inputName = "input.txt";
    private final static String outputName = "output.txt";
    private static Parser parser;
    
    public static void main(String[] args) {
    	KnowledgeBase KB = parseInput(inputName);
    	if(KB != null) {
    		if(searchKB(KB, KB.getQuery())) {
    			printOutput("TRUE");
    		}
    		else {
    			printOutput("FALSE");
    		}
    	}
    }
    

        private static void printOutput(String msg) {
    	parser.printOutput(msg);
    }
    
    private static KnowledgeBase parseInput(String input) {
    	try {
    		parser = new Parser(input, outputName);
    		KnowledgeBase KB = parser.getKB();
    		return KB;
    	}
    	catch(FileNotFoundException e) {
    		System.out.println("File Not Found: " + inputName);
    	}
    	return null;
    }

	private static boolean searchKB(KnowledgeBase KB, Predicate query) {
    	boolean canInfer = true;
    	ArrayList<Substitution> sublist = new ArrayList<Substitution>();
    	sublist = generatorOR(KB, query, sublist);
    	
    	if(sublist == null) {
    		canInfer = false;
    	}
    	
    	return canInfer;
    }
	
	private static ArrayList<Substitution> generatorAND(KnowledgeBase KB, ArrayList<Predicate> goals, ArrayList<Substitution> sublist) {
    	ArrayList<Substitution> subsRestGoals = new ArrayList<Substitution>();
    	if(sublist == null) {
    		return null;
    	}
    	else if(goals == null || goals.size() == 0) {
    		return sublist;
    	}
    	else {
    		Predicate firstGoal = goals.get(0);
    		goals.remove(0);
    		
    		ArrayList<Substitution> newSubs = generatorOR(KB, Substitute(sublist, firstGoal), sublist);
    		subsRestGoals = generatorAND(KB, goals, newSubs);
    	}
    	return subsRestGoals;
    }
    
    private static ArrayList<Substitution> generatorOR(KnowledgeBase KB, Predicate goal, ArrayList<Substitution> sublist) {
    	ArrayList<Rule> matchingRules = KB.fetchRules(goal);;
    	
    	if(matchingRules.size() == 0) {
    		return null;
    	}
    	for(Rule currRule : matchingRules) {
    		ArrayList<Substitution> newSubs = generatorAND(KB, currRule.getLHS(), Unify(currRule.getRHS(), goal, sublist));
    		if(newSubs == null) {
    			return null;
    		}
    		else {
    			sublist.addAll(newSubs);
    		}
    	}
    	return sublist;
    }
	



	

    

}