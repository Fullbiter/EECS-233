/**
 * WordGraph.java
 *
 * Kevin Nash (kjn33)
 * 12/03/2014
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class WordGraph {
    
    private ArrayList<String> fullText; // Every word, represented as a String, in order of appearance
    private HashTable nodeTable; // A HashTable contains a node for each unique word
    
    public WordGraph(String fileName) throws java.io.IOException {
        Tokenizer tok = new Tokenizer(fileName);
        fullText = tok.wordList();
        HashSet<String> wordSet = new HashSet<String>();
        for (String word : fullText)
            wordSet.add(word);
        nodeTable = new HashTable(wordSet.size() * 2);
        // Represent each unique word with a node
        for (String word : wordSet)
            nodeTable.put(word, 0);
        for (int i = 0; i < fullText.size(); i++) {
            // Connect the node to the node of the previous word if possible
            if (i > 0)
                nodeTable.get(fullText.get(i)).addPrev(nodeTable.get(fullText.get(i - 1)));
            // Connect the node to the node of the next word if possible
            if (i < fullText.size() - 1)
                nodeTable.get(fullText.get(i)).addNext(nodeTable.get(fullText.get(i + 1)));
            nodeTable.increment(fullText.get(i));
        }
    }
    
    public static void main(String[] args) throws java.io.IOException {
        System.out.println("The program will use Asimov's short story The Last Question as a test document.");
        WordGraph test = new WordGraph("the_last_question.txt");
        System.out.println("There are " + test.numNodes() + " unique words in the document.");
        System.out.println("There are " + test.numEdges() + " unique word pairs in the document.");
        System.out.println("The word \"space\" appears " + test.wordCount("space") + " times in the document.");
        System.out.println(test.inDegree("space") + " unique words appear before the word \"space\".");
        System.out.println("These " + test.inDegree("space") + " words are: ");
        for (String word : test.prevWords("space"))
            System.out.print(word + ", ");
        System.out.println();
        System.out.println(test.outDegree("space") + " unique words appear after the word \"space\".");
        System.out.println("These " + test.outDegree("space") + " words are: ");
        for (String word : test.nextWords("space"))
            System.out.print(word + ", ");
        System.out.println();
        System.out.println("The cost of the sequence \"space grew black\" is " + 
                           test.wordSeqCost(new String[] {"space", "grew", "black"}));
        System.out.println("The cost of the sequence \"does_not_exist\" is " + 
                           test.wordSeqCost(new String[] {"does_not_exist"}));
        System.out.println("A plausable phrase starting with \"space\" and ending with \"black\" is: " + 
                           test.generatePhrase("space", "black", 3));
    }
    
    // Returns the size of the graph
    public int numNodes() {
        return nodeTable.size();
    }
    
    public int numEdges() {
        int numEdges = 0;
        for (LinkedList<WordNode> chain : nodeTable) {
            for (WordNode node : chain)
                numEdges += node.getSucceeding().size();
        }
        return numEdges;
    }
    
    // Returns the number of occurances of a specified word
    public int wordCount(String w) {
        if (nodeTable.get(w) != null)
            return nodeTable.get(w).getCount();
        return 0;
    }
    
    // Returns the number of occurances of a specified pair of words
    public int wordPairCount(String w1, String w2) {
        if (nodeTable.get(w1) != null && nodeTable.get(w1).getSucceeding().get(new WordNode(w2, 0)) != null)
            return nodeTable.get(w1).getSucceeding().get(nodeTable.get(w2));
        return 0;
    }
    
    // Returns the number of unique words that preceed the specified word
    public int inDegree(String w) {
        return nodeTable.get(w).getPreceeding().size();
    }
    
    // Returns the number of unique words that follow the specified word
    public int outDegree(String w) {
        return nodeTable.get(w).getSucceeding().size();
    }
    
    // Returns an array of words that preceed the given word, sorted alphabetically
    public String[] prevWords(String w) {
        WordNode[] prevNodes = nodeTable.get(w).getPreceeding().keySet().toArray(new WordNode[inDegree(w)]);
        String[] prevWords = new String[prevNodes.length];
        for (int i = 0; i < prevNodes.length; i++)
            prevWords[i] = prevNodes[i].getWord();
        return prevWords;
    }
    
    // Returns an array of words that succeed the given word, sorted alphabetically
    public String[] nextWords(String w) {
        WordNode[] nextNodes = nodeTable.get(w).getSucceeding().keySet().toArray(new WordNode[outDegree(w)]);
        String[] nextWords = new String[nextNodes.length];
        for (int i = 0; i < nextNodes.length; i++)
            nextWords[i] = nextNodes[i].getWord();
        return nextWords;
    }
    
    public double wordSeqCost(String[] wordSeq) {
        double wordSeqCost = 0.0;
        if (wordSeq.length > 0) {
            wordSeqCost = Math.log(fullText.size() / (double)wordCount(wordSeq[0]));
            for (int i = 0; i < wordSeq.length - 1; i++)
                wordSeqCost += Math.log(wordCount(wordSeq[i]) / (double)wordPairCount(wordSeq[i], wordSeq[i + 1]));
        }
        return wordSeqCost;
    }
    
    public double wordPairCost(String w1, String w2) {
        return Math.log(wordCount(w1) / (double)wordPairCount(w1, w2));
    }
    
    public String generatePhrase(String startWord, String endWord, int n) {
        if (n > 0) {
            StringBuilder phrase = new StringBuilder();
            phrase.append(startWord + " ");
            TreeMap<Double, WordNode> neighborCosts = new TreeMap<Double, WordNode>();
            for (Map.Entry<WordNode, Integer> entry : nodeTable.get(startWord).getSucceeding().entrySet())
                neighborCosts.put(new Double(wordPairCost(startWord, entry.getKey().getWord())), entry.getKey());
            phrase.append(generatePhrase(neighborCosts.firstEntry().getValue().getWord(), endWord, n - 1));
            phrase.append(" " + endWord);
            return phrase.toString();
        }
        return "";
    }
}