/**
 * WordStatTest.java
 *
 * Kevin Nash (kjn33)
 * 11/11/2014
 */
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class WordStatTest {
    
    @Test
    public void stringArrayConstructor() {
        WordStat ws = new WordStat(new String[] {"this", "is", "a", "test"});
        assertEquals(1, ws.wordCount("test"));
    }
    
    @Test
    public void emptyStringArrayConstructor() {
        WordStat ws = new WordStat(new String[0]);
        assertEquals(0, ws.wordCount("test"));
    }
    
    @Test
    public void wordCountZero() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertEquals(0, ws.wordCount(""));
    }
    
    @Test
    public void wordCountOne() throws IOException {
        WordStat ws = new WordStat("test_single_word.txt");
        assertEquals(1, ws.wordCount("test"));
    }
    
    @Test
    public void wordCountMany() throws IOException {
        WordStat ws = new WordStat("test_large.txt");
        assertEquals(6, ws.wordCount("bacon"));
    }
    
    @Test
    public void wordCountNonNormalized() throws IOException {
        WordStat ws = new WordStat("test_hyphen.txt");
        assertEquals(1, ws.wordCount("TOP-NOTCH"));
    }
    
    @Test
    public void wordRankBasic() throws IOException {
        WordStat ws = new WordStat("test_single_word.txt");
        assertEquals(1, ws.wordRank("test"));
    }
    
    @Test
    public void wordRankDoesNotExist() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertEquals(-1, ws.wordRank(""));
    }
       
    @Test
    public void wordRankTie() throws IOException {
        WordStat ws = new WordStat(new String[] {"one", "one", "two", "two", "three", "four"});
        assertEquals(1, ws.wordRank("one"));
        assertEquals(1, ws.wordRank("two"));
        assertEquals(3, ws.wordRank("three"));
        assertEquals(3, ws.wordRank("four"));
    }
    
    @Test
    public void wordRankNonNormalized() throws IOException {
        WordStat ws = new WordStat("test_hyphen.txt");
        assertEquals(1, ws.wordRank("TOP-NOTCH"));
    }
    
    @Test
    public void mostCommonWordsBasic() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertArrayEquals(new String[] {"pork"}, ws.mostCommonWords(1));
    }
    
    @Test
    public void mostCommonWordsEmpty() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[0], ws.mostCommonWords(0));
    }
    
    @Test
    public void mostCommonWordsAlphabetize() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[] {"a", "is", "test", "this"}, ws.mostCommonWords(4));
    }
    
    @Test
    public void mostCommonWordsRequestTooLarge() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[] {"a", "is", "test", "this"}, ws.mostCommonWords(99));
    }
    
    @Test
    public void leastCommonWordsBasic() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertArrayEquals(new String[] {"belly"}, ws.leastCommonWords(1));
    }
    
    @Test
    public void leastCommonWordsEmpty() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[0], ws.leastCommonWords(0));
    }
    
    @Test
    public void leastCommonWordsAlphabetize() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[] {"a", "is", "test", "this"}, ws.leastCommonWords(4));
    }
    
    @Test
    public void leastCommonWordsRequestTooLarge() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[] {"a", "is", "test", "this"}, ws.leastCommonWords(99));
    }
    
    @Test
    public void wordPairCountZero() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertEquals(0, ws.wordPairCount("", ""));
    }
    
    @Test
    public void wordPairCountOne() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertEquals(1, ws.wordPairCount("a", "test"));
    }
    
    @Test
    public void wordPairCountMany() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertEquals(4, ws.wordPairCount("ham", "hock"));
    }
    
    @Test
    public void wordPairCountNonNormalized() throws IOException {
        WordStat ws = new WordStat("test_hyphen.txt");
        assertEquals(1, ws.wordPairCount("TOP-NOTCH", "test"));
    }
    
    @Test
    public void wordPairRankBasic() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertEquals(1, ws.wordPairRank("a", "test"));
    }
    
    @Test
    public void wordPairRankDoesNotExist() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertEquals(-1, ws.wordPairRank("", ""));
    }
       
    @Test
    public void wordPairRankTie() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertEquals(1, ws.wordPairRank("corned", "beef"));
        assertEquals(2, ws.wordPairRank("filet", "mignon"));
        assertEquals(2, ws.wordPairRank("ham", "hock"));
        assertEquals(2, ws.wordPairRank("short", "loin"));
        assertEquals(5, ws.wordPairRank("ball", "tip"));
    }
    
    @Test
    public void wordPairRankNonNormalized() throws IOException {
        WordStat ws = new WordStat("test_hyphen.txt");
        assertEquals(1, ws.wordPairRank("TOP-NOTCH", "test"));
    }
    
    @Test
    public void mostCommonCollocsPreceeding() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertArrayEquals(new String[] {"andouille", "fatback"}, ws.mostCommonCollocs(2, "sausage", -1));
    }

    @Test
    public void mostCommonCollocsSucceeding() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertArrayEquals(new String[] {"loin", "chop"}, ws.mostCommonCollocs(2, "pork", 1));
    }

    @Test
    public void mostCommonCollocsEmpty() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[0], ws.mostCommonCollocs(0, "test", 1));
    }
    
    @Test
    public void mostCommonCollocsExcAlphabetize() throws IOException {
        WordStat ws = new WordStat("test_long_document.txt");
        assertArrayEquals(new String[] {"picanha", "pork"}, ws.mostCommonCollocs(2, "steak", 1));
    }
    
    @Test
    public void mostCommonCollocsRequestTooLarge() throws IOException {
        WordStat ws = new WordStat("test.txt");
        assertArrayEquals(new String[] {"is"}, ws.mostCommonCollocs(99, "this", 1));
    }
}