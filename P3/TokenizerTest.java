/**
 * TokenizerTest.java
 *
 * Kevin Nash (kjn33)
 * 11/11/2014
 */
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class TokenizerTest {
    
    @Test
    public void stringArrayConstructor() {
        Tokenizer tok = new Tokenizer(new String[] {"this", "is", "a", "test"});
        ArrayList<String> template = new ArrayList<String>();
        template.add("this");
        template.add("is");
        template.add("a");
        template.add("test");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void emptyStringArrayConstructor() {
        Tokenizer tok = new Tokenizer(new String[0]);
        ArrayList<String> template = new ArrayList<String>();
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void basicToken() throws IOException {
        // Handle basic functionality
        Tokenizer tok = new Tokenizer("test.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("this");
        template.add("is");
        template.add("a");
        template.add("test");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void accentToken() throws IOException {
        // Handle words containing accented characters
        Tokenizer tok = new Tokenizer("test_accent.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("you");
        template.add("may");
        template.add("be");
        template.add("a");
        template.add("na"); // naive
        template.add("ve");
        template.add("person");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void apostropheToken() throws IOException {
        // Handle words containing apostrophes
        Tokenizer tok = new Tokenizer("test_apostrophe.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("lets"); // let's
        template.add("see");
        template.add("if");
        template.add("its"); // it's
        template.add("working");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void digitToken() throws IOException {
        // Handle words made up of digits
        Tokenizer tok = new Tokenizer("test_digit.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("this");
        template.add("test");
        template.add("was");
        template.add("created");
        template.add("circa");
        template.add("2014");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void duplicateToken() throws IOException {
        // Handle duplicate words in the same line
        Tokenizer tok = new Tokenizer("test_duplicate.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("read");
        template.add("to");
        template.add("me");
        template.add("what");
        template.add("you");
        template.add("have");
        template.add("read");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void hyphenToken() throws IOException {
        // Handle hyphenated words
        Tokenizer tok = new Tokenizer("test_hyphen.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("this");
        template.add("is");
        template.add("a");
        template.add("topnotch"); // top-notch
        template.add("test");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void newLineToken() throws IOException {
        // Handle words separated by new lines
        Tokenizer tok = new Tokenizer("test_newline.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("this");
        template.add("is");
        template.add("a");
        template.add("test");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void singleWordToken() throws IOException {
        // Handle a file containing a single word
        Tokenizer tok = new Tokenizer("test_single_word.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("test");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void upperCaseToken() throws IOException {
        // Handle words written only in capital letters
        Tokenizer tok = new Tokenizer("test_uppercase.txt");
        ArrayList<String> template = new ArrayList<String>();
        template.add("this");
        template.add("is");
        template.add("a");
        template.add("test");
        assertEquals(template, tok.wordList());
    }
    
    @Test
    public void orphanPunctuationToken() throws IOException {
        // Handle words containing only acceptable punctuation
        Tokenizer tok = new Tokenizer(new String[] {"test", "-", "'"});
        ArrayList<String> template = new ArrayList<String>();
        template.add("test");
        assertEquals(template, tok.wordList());
    }
}