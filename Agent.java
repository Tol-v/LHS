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
    

    

    

	



	

    

}