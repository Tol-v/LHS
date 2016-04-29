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
}