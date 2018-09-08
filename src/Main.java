import java.io.*;
import java.lang.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Nastya on 11.10.2017.
 */
public class Main {

    public static String replaceSpaces(String input){
        return input.replaceAll("\\s[+]\\s","+").replaceAll("\\s[-]\\s","-").replaceAll("\\s[*]\\s","*").replaceAll("\\s[/]\\s","/");
    }

    public static void runTests() {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int lineNum = 1;
            try {
                PrintWriter wr = new PrintWriter("output.txt");
                while (scanner.hasNextLine())
                {
                    System.out.println("test " + lineNum);
                    String input = replaceSpaces(scanner.nextLine());
                    input = input.replaceAll("\\s", "");
                    Parser parser = new Parser(input);
                    Expression exprTree = parser.parse();
//                    System.out.println(exprTree.toString());
                    long result = exprTree.calculate();
                    System.out.println("result: " + result);
                    wr.println(String.valueOf(result));
                    lineNum++;
                }
                wr.close();
            } catch (IOException ex) {
            }
        } catch (FileNotFoundException x) {
        }
    }

    public static void main(String[] args) {
        runTests();
    }
}
