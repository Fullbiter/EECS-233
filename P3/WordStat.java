/**
 * WordStat.java
 *
 * Kevin Nash (kjn33)
 * 11/06/2014
 */
import java.util.*;
import java.util.Map.Entry;

public class WordStat {
    
    // Key: word, Value: count of word
    private HashTable wordCountTable;
    
    // Key: count, Value: words whose counts match
    private TreeMap<Integer, LinkedList<String>> wordRankMap
        = new TreeMap<Integer, LinkedList<String>>(Collections.reverseOrder());
    
    // Key: word pair, Value: count of word pair
    private HashTable pairCountTable;
    
    // Key: count, Value: word pairs whose counts match
    private TreeMap<Integer, LinkedList<String>> pairRankMap
        = new TreeMap<Integer, LinkedList<String>>(Collections.reverseOrder());
    
    public WordStat(String[] s) {
        Tokenizer tok = new Tokenizer(s);
        populateCountTables(tok);
        populateRankMaps();
    }
    
    public WordStat(String fileName) throws java.io.IOException {
        Tokenizer tok = new Tokenizer(fileName);
        populateCountTables(tok);
        populateRankMaps();
    }
    
    public int wordCount(String word) {
        word = normalize(word);
        if (wordCountTable.get(word) != -1) {
            return wordCountTable.get(word);
        }
        return 0;
    }
    
    public int wordRank(String word) {
        int wordCount = wordCount(word); // wordCount() need not be called more than once
        int rank = 1;
        for (Entry<Integer, LinkedList<String>> entry : wordRankMap.entrySet()) {
            if (wordCount == entry.getKey())
                return rank;
            rank += entry.getValue().size(); // change this to rank++ if consecutive ranking is desired
        }
        return -1;
    }            
    
    public String[] mostCommonWords(int k) {
        String[] mostCommonWords = new String[k];
        int wordsRemaining = k;
        int actualLength = 0; // Limits the length of mostCommonWords to total number of words
        for (Entry<Integer, LinkedList<String>> entry : wordRankMap.entrySet()) {
            Iterator<String> itr = entry.getValue().iterator();
            while (itr.hasNext() && wordsRemaining > 0) {
                actualLength++;
                mostCommonWords[k - wordsRemaining--] = itr.next();
            }
        }
        // Limit the length of mostCommonWords to total number of words
        String[] trimmedArray = new String[actualLength];
        for (int j = 0; j < actualLength; j++)
            trimmedArray[j] = mostCommonWords[j];
        return trimmedArray;
    }
    
    public String[] leastCommonWords(int k) {
        Map<Integer, LinkedList<String>> reverseRankMap = wordRankMap.descendingMap();
        String[] leastCommonWords = new String[k];
        int wordsRemaining = k;
        int actualLength = 0; // Limits the length of leastCommonWords to total number of words
        for (Entry<Integer, LinkedList<String>> entry : reverseRankMap.entrySet()) {
            Iterator<String> itr = entry.getValue().iterator();
            while (itr.hasNext() && wordsRemaining > 0) {
                actualLength++;
                leastCommonWords[k - wordsRemaining--] = itr.next();
            }
        }
        // Limit the length of leastCommonWords to total number of words
        String[] trimmedArray = new String[actualLength];
        for (int j = 0; j < actualLength; j++)
            trimmedArray[j] = leastCommonWords[j];
        return trimmedArray;
    }
    
    public int wordPairCount(String w1, String w2) {
        w1 = normalize(w1);
        w2 = normalize(w2);
        if (pairCountTable.get(w1 + " " + w2) != -1) {
            return pairCountTable.get(w1 + " " + w2);
        }
        return 0;
    }
    
    public int wordPairRank(String w1, String w2) {
        int wordPairCount = wordPairCount(w1, w2); // wordPairCount() need not be called more than once
        int rank = 1;
        for (Entry<Integer, LinkedList<String>> entry : pairRankMap.entrySet()) {
            if (wordPairCount == entry.getKey())
                return rank;
            rank += entry.getValue().size(); // change this to rank++ if consecutive ranking is desired
        }
        return -1;
    }
    
    public String[] mostCommonWordPairs(int k) {
        String[] mostCommonPairs = new String[k];
        int pairsRemaining = k;
        int actualLength = 0; // Limits the length of mostCommonPairs to total number of pairs
        for (Entry<Integer, LinkedList<String>> entry : pairRankMap.entrySet()) {
            Iterator<String> itr = entry.getValue().iterator();
            while (itr.hasNext() && pairsRemaining > 0) {
                actualLength++;
                mostCommonPairs[k - pairsRemaining--] = itr.next();
            }
        }
        // Limit the length of mostCommonPairs to total number of pairs
        String[] trimmedArray = new String[actualLength];
        for (int j = 0; j < actualLength; j++)
            trimmedArray[j] = mostCommonPairs[j];
        return trimmedArray;
    }
    
    public String[] mostCommonCollocs(int k, String baseWord, int i) {
        return mostCommonCollocsExc(k, baseWord, i, new String[0]);
    }
    
    public String[] mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions) {
        baseWord = normalize(baseWord);
        String[] mostCommonCollocs = new String[k];
        boolean isExcluded = false;
        int collocsRemaining = k;
        int actualLength = 0; // Limits the length of mostCommonCollocs to total number of collocs
        HashTable collocPairs = new HashTable();
        TreeMap<Integer, LinkedList<String>> collocRankMap
            = new TreeMap<Integer, LinkedList<String>>(Collections.reverseOrder());
        // Generate a HashTable of pairs that include baseWord as their first word
        for (LinkedList<HashEntry> chain : pairCountTable) {
            for (HashEntry entry : chain) {
                if (i == 1 && entry.getKey().startsWith(baseWord + " ")) {
                    for (String exclusion : exclusions) {
                        if (entry.getKey().endsWith(exclusion)) {
                            isExcluded = true;
                            break;
                        }
                    }
                    if (!isExcluded)
                        collocPairs.update(entry.getKey().replace(baseWord + " ", ""), entry.getValue());
                    isExcluded = false;
                }
                if (i == -1 && entry.getKey().endsWith(" " + baseWord)) {
                    for (String exclusion : exclusions) {
                        if (entry.getKey().startsWith(exclusion)) {
                            isExcluded = true;
                            break;
                        }
                    }
                    if (!isExcluded)
                        collocPairs.update(entry.getKey().replace(" " + baseWord, ""), entry.getValue());
                    isExcluded = false;
                }
            }
        }
        // Generate a TreeMap of counts of collocPairs
        for (LinkedList<HashEntry> chain : collocPairs) {
            for (HashEntry entry : chain) {
                LinkedList<String> collocsWithSameCount;
                if (collocRankMap.get(entry.getValue()) == null)
                    collocsWithSameCount = new LinkedList<String>();
                else
                    collocsWithSameCount = collocRankMap.get(entry.getValue());
                collocsWithSameCount.add(entry.getKey());
                Collections.sort(collocsWithSameCount);
                collocRankMap.put(new Integer(entry.getValue()), collocsWithSameCount);
            }
        }
        // Copy the first k values into the common collocations array
        for (Entry<Integer, LinkedList<String>> entry : collocRankMap.entrySet()) {
            Iterator<String> itr = entry.getValue().iterator();
            while (itr.hasNext() && collocsRemaining > 0) {
                actualLength++;
                mostCommonCollocs[k - collocsRemaining--] = itr.next();
            }
        }
        // Limit the length of mostCommonCollocs to total number of collocs
        String[] trimmedArray = new String[actualLength];
        for (int j = 0; j < actualLength; j++)
            trimmedArray[j] = mostCommonCollocs[j];
        return trimmedArray;
    }
    
    public String generateWordString(int k, String startWord) {
        StringBuilder sb = new StringBuilder(startWord);
        if (k > 1 && mostCommonCollocs(1, startWord, 1).length > 0) {
            sb.append(" ");
            sb.append(generateWordString(--k, mostCommonCollocs(1, startWord, 1)[0]));
        }
        return sb.toString();
    }
    
    private String normalize(String word) {
        Tokenizer tok = new Tokenizer(new String[] {word});
        if (tok.wordList().size() > 0)
            word = tok.wordList().get(0);
        return word;
    }
    
    private void populateCountTables(Tokenizer tok) {
        // Use a set to determine unique words
        HashSet<String> uniqueWordList = new HashSet<String>();
        for (String word : tok.wordList())
            uniqueWordList.add(word);
        // Allocate twice as many buckets as there are words
        wordCountTable = new HashTable(uniqueWordList.size() * 2);
        pairCountTable = new HashTable(uniqueWordList.size() * 2);
        boolean firstWord = true; // Flag that is only true when the first word is examined
        StringBuilder evenWordPair = new StringBuilder(); // Pair whose first word is even (e.g. 26th word)
        StringBuilder oddWordPair = new StringBuilder();  // Pair whose first word is odd (e.g. 25th word)
        int wordsInEvenPair = 0; // Flag that indicates when to append a space (1) or submit the pair (2)
        int wordsInOddPair = 0;
        for (String word : tok.wordList()) {
            wordCountTable.increment(word);
            oddWordPair.append(word);
            wordsInOddPair++;
            if (wordsInOddPair == 1)
                oddWordPair.append(" ");
            if (!firstWord) {
                evenWordPair.append(word);
                wordsInEvenPair++;
                if (wordsInEvenPair == 1)
                    evenWordPair.append(" ");
                if (wordsInOddPair == 2) {
                    pairCountTable.increment(oddWordPair.toString());
                    oddWordPair.setLength(0);
                    oddWordPair.trimToSize();
                    wordsInOddPair = 0;
                }
                if (wordsInEvenPair == 2) {
                    pairCountTable.increment(evenWordPair.toString());
                    evenWordPair.setLength(0);
                    evenWordPair.trimToSize();
                    wordsInEvenPair = 0;
                }
            }
            firstWord = false;
        }
    }
    
    private void populateRankMaps() {
        // Populate wordRankMap 
        for (LinkedList<HashEntry> chain : wordCountTable) {
            for (HashEntry entry : chain) {
                LinkedList<String> wordsWithSameCount;
                // For a given count, add the word to the existing List of words or to a new list if none exists
                if (wordRankMap.get(entry.getValue()) == null)
                    wordsWithSameCount = new LinkedList<String>();
                else
                    wordsWithSameCount = wordRankMap.get(entry.getValue());
                wordsWithSameCount.add(entry.getKey());
                Collections.sort(wordsWithSameCount);
                wordRankMap.put(new Integer(entry.getValue()), wordsWithSameCount);
            }
        }
        // Populate pairRankMap
        for (LinkedList<HashEntry> chain : pairCountTable) {
            for (HashEntry entry : chain) {
                LinkedList<String> pairsWithSameCount;
                // For a given count, add the pair to the existing List of pairs or to a new list if none exists
                if (pairRankMap.get(entry.getValue()) == null)
                    pairsWithSameCount = new LinkedList<String>();
                else
                    pairsWithSameCount = pairRankMap.get(entry.getValue());
                pairsWithSameCount.add(entry.getKey());
                Collections.sort(pairsWithSameCount);
                pairRankMap.put(new Integer(entry.getValue()), pairsWithSameCount);
            }
        }
    }
    
    public void print() {
        for (Entry entry : pairRankMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}