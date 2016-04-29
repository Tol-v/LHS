import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private final int QUERY_NUM_LINES = 1;
    private final int NUMSENTENCES_NUM_LINES = 1;

    private Scanner scanner;
    private String outputName;
    private KnowledgeBase KB;

    public Parser(String inputName, String outputName) throws FileNotFoundException {

        File inputFile = new File(inputName);
        scanner = new Scanner(inputFile);

        this.outputName = outputName;
        //setupOutputFile();

        try {
            readInputFile();
        }
        catch(IOException ioe) {
            System.out.println("I/O Exception while reading " + inputName);
        }
    }

    private void readInputFile() throws IOException {
        String unparsedQuery = readLines(QUERY_NUM_LINES, scanner).get(0); //query = one line
        Predicate query = readPredicate(unparsedQuery);
        ArrayList<Rule> KB_Rules = new ArrayList<Rule>();

        int numClauses = Integer.parseInt(readLines(NUMSENTENCES_NUM_LINES, scanner).get(0));

        ArrayList<String> unparsedPredicates = readLines(numClauses, scanner);

        for(String unparsedPredicate : unparsedPredicates) {
            Rule currRule = null;
            if(!hasLHS(unparsedPredicate)) {
                Predicate pred =  readPredicate(unparsedPredicate);
                currRule = new Rule(pred, null);
            }
            else {//separate each predicate into RH and LH sides
                String rhs = getRHS(unparsedPredicate);
                String lhs = getLHS(unparsedPredicate);
                ArrayList<String> lhsArr = separateLHS(lhs);

                ArrayList<Predicate> lhsPreds = new ArrayList<Predicate>();
                for(String lhsPred : lhsArr) {
                    Predicate pred = readPredicate(lhsPred);
                    lhsPreds.add(pred);
                }

                Predicate rhsPred =  readPredicate(rhs);

                currRule = new Rule(rhsPred, lhsPreds);
            }

            KB_Rules.add(currRule);
        }

        this.KB = new KnowledgeBase(query, KB_Rules);
    }
}