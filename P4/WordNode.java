/**
 * WordNode.java
 *
 * Kevin Nash (kjn33)
 * 12/03/2014
 */
import java.util.TreeMap;

public class WordNode implements Comparable<WordNode> {
    private String word;
    private int count;
    private double cost = Double.POSITIVE_INFINITY;
    private TreeMap<WordNode, Integer> preceeding = new TreeMap<WordNode, Integer>();
    private TreeMap<WordNode, Integer> succeeding = new TreeMap<WordNode, Integer>();
    
    public WordNode(String word, int count) {
        this.word = word;
        this.count = count;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
    
    public String getWord() {
        return word;
    }
    
    public void addPrev(WordNode prevWord) {
        if (preceeding.get(prevWord) != null)
            preceeding.put(prevWord, preceeding.get(prevWord) + 1);
        else
            preceeding.put(prevWord, 1);
    }
    
    public void addNext(WordNode nextWord) {
        if (succeeding.get(nextWord) != null)
            succeeding.put(nextWord, succeeding.get(nextWord) + 1);
        else
            succeeding.put(nextWord, 1);
    }
    
    public TreeMap<WordNode, Integer> getPreceeding() {
        return preceeding;
    }
    
    public TreeMap<WordNode, Integer> getSucceeding() {
        return succeeding;
    }
    
    public int compareTo(WordNode other) {
        return this.getWord().compareTo(other.getWord());
    }
}