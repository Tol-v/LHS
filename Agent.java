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

    

	



	

    

}