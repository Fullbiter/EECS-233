/**
 * Tokenizer.java
 *
 * Kevin Nash (kjn33)
 * 11/04/2014
 */
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {
    
    private String[] fullText = new String[0];
    private ArrayList<String> wordList = new ArrayList<String>();
    
    public Tokenizer(String[] s) {
        fullText = s;
        extractWords();
    }
    
    // Reads a specified file into a String[]
    public Tokenizer(String fileName) throws java.io.IOException {
        fullText = Files.readAllLines(Paths.get(fileName), Charset./*forName("US-ASCII")*/defaultCharset()).toArray(fullText);
        extractWords();
    }
    
    public ArrayList<String> wordList() {
        return wordList;
    }
    
    // Prints the contents of wordList for testing purposes
    public void print() {
        for (String s : wordList)
            System.out.println(s);
    }
    
    // Populates wordList with word Strings
    // ASCII compatible English is supported
    // Hyphenated words and contractions are not separated into multiple words
    private void extractWords() {
        StringBuilder word = new StringBuilder();
        String alphanumeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-'";
        boolean prevMatched = false;
        for (String line : fullText) {
            for (int i = 0; i < line.length(); i++) {
                if (alphanumeric.indexOf(line.charAt(i)) >= 0) {
                    if (line.charAt(i) != '-' && line.charAt(i) != '\'')
                        word.append(line.charAt(i));
                    prevMatched = true;
                }
                else if (prevMatched) {
                    wordList.add(word.toString().toLowerCase());
                    word.setLength(0);
                    word.trimToSize();
                    prevMatched = false;
                }
            }
            // This block ensures that words are split between lines,
            // and that the last word in a line is read
            if (prevMatched) {
                wordList.add(word.toString().toLowerCase());
                word.setLength(0);
                word.trimToSize();
                prevMatched = false;
            }
        }
        // Remove empty strings created by lone apostrophes and hyphens
        wordList.removeAll(Arrays.asList(""));
    }
}