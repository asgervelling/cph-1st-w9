import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;;

public class Main {
    static Scanner scan;
    private static String[][] text = new String[12][1];

    public static void main(String[] args) throws FileNotFoundException {

        // Load words into String[][] text
        File file = new File("data.txt");
        scan = new Scanner(file);
        int i = 0;
        while (scan.hasNextLine()) {
            text[i] = scan.nextLine().split(" ");
            i++;
        }

        // todo: print alle ord der starter med det der sendes med som argument,
        System.out.println("___________________");
        System.out.println("Print words starting with Ø:");
        printWordsStartingWith("Ø");        

        //todo: print alle ord der har præcis det antal bogstaver der sendes med som argument (inkluderer IKKE tal og tegnene '.' og ',')
        System.out.println("___________________");
        System.out.println("Print words of length 3:");
        printWordsOfLength(3);
     
        //test dine metoder ved at kalde dem her:
        System.out.println("___________________");
        System.out.println("Print words with double consonants, i.e. \"ll\" or \"mm\":");
        printWordsWithDoubleConsonant();

        System.out.println("___________________");
        System.out.println("Print the longest sentence in data.txt:");
        printLongestSentence();

        System.out.println("___________________");
        System.out.println("Print part of a word:");
        printPartOfWord("københavn", 2, 4);
        printPartOfWord("københavn", 2, 19);

        System.out.println("___________________");
        System.out.println("Print if palindrome:");
        printIfPalindrome("København");
        printIfPalindrome("Den laks skal ned");
        
    }

    private static void printWordsOfLength(int l) {
        boolean wordisvalid = true;

        for (int i = 0; i < text.length; i++) {
            for (String s : text[i]) {
                if (s.length() == l) {
                    if (s.contains(",") || s.contains(".")) {
                        wordisvalid = false;
                    }
                     /*for (int j = 0; j < s.length(); j++) {
                         char c = s.charAt(j);
                         if(c == '.' || c == ','){
                             wordisvalid = false;
                         }
                     }*/
                    if (wordisvalid) {
                        System.out.print(s + ", ");
                    }

                }
            }
        }
        System.out.print("\n");
    }

    private static void printWordsStartingWith(String pattern) {

        for (int i = 0; i < text.length; i++) {
            for (String s : text[i]) {
                if (s.startsWith(pattern) || s.startsWith(pattern.toLowerCase())) {
                    System.out.print(s + ", ");
                }
            }

        }
        System.out.print("\n");
    }

    //skriv dine metoder herunder:
    private static void printWordsWithDoubleConsonant() {
        try {
            Pattern pattern = Pattern.compile("([^aeiouyæøå\\s0-9])\\1");
            for (int i = 0; i < text.length; i++) {
                for (String s : text[i]) {
                    Matcher matcher = pattern.matcher(s);
                    boolean matchFound = matcher.find();
                    if (matchFound) {
                        System.out.print(s + ", ");
                    }
                }
            }

        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
        System.out.print("\n");
    }

    private static void printLongestSentence() {
        String longest = "";

        Scanner scanner = null;
        try {
            File file = new File("data.txt");
            scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split("\\.");
                longest = s[0];
                for (String sentence : s) {
                    if (sentence.length() > longest.length()) {
                        longest = sentence;
                    }
                }
                
            }
            System.out.println("Longest: " + longest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("\n");
    }

    private static void printPartOfWord(String word, int start, int len) {
        try {
            String subStr = word.substring(start, start + len);
            System.out.print(subStr);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.format("You tried to print indices %d-%d,\n" +
                                "but the word \"%s\" only has %d characters.",
                                start, start + len,
                                word, word.length());
        }
        System.out.print("\n");
    }

    private static void printIfPalindrome(String word) {
        System.out.format("%s %s a palindrome.\n", word, (isPalindrome(word) ? "is" : "is not"));
        
    }

    private static boolean isPalindrome(String word) {
        word = word.toLowerCase();
        char forwards;
        char backwards;
        for (int i = 0; i < word.length(); i++) {
            forwards = word.charAt(i);
            backwards = word.charAt(word.length() - 1 - i);
            
            if (backwards != forwards) {
                return false;
            }
        }
        return true;
    }
}
