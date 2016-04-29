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
	
private static ArrayList<Substitution> Unify(Predicate rhs, Predicate goal, ArrayList<Substitution> sublist) {
    	//RHS obtained from KB
    	if(rhs.getName().equals(goal.getName())) {
    		if(rhs.hasVariables() || goal.hasVariables()) {//SUBCASE I: RHS has a variable, make substitution and update sublist
				Substitution newSub = new Substitution(rhs, goal);
    			boolean alreadyExists = false;
    			for(Substitution currSub : sublist) {
    				if(newSub.equals(currSub)) {
    					alreadyExists = true;
    				}
    			}
    			if(!alreadyExists) {
    				sublist.add(newSub);
    			}
    		}
    		else if(!rhs.equals(goal)) {//SUBCASE II: RHS has only objects; if RHS != goal, return null
    			sublist = null;
    		}
    		//SUBCASE III: RHS RHS has only objects; if RHS = goal, return sublist
    	}
    	return sublist;
    }
    
    private static Predicate Substitute(ArrayList<Substitution> sublist, Predicate goal) {
    	Predicate newPred = null;
    	for(Substitution sub : sublist) {
    		newPred = sub.makeSubstitution(goal);
    		if(newPred != null) {
    			break;
    		}
    	}
    	
    	if(newPred != null) {
    		goal = newPred;
    	}
    	return goal;
    }


	

    

}