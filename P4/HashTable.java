/**
 * HashTable.java
 *
 * Kevin Nash (kjn33)
 * 12/04/2014
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable implements Iterable<LinkedList<WordNode>> {
    
    private ArrayList<LinkedList<WordNode>> table;
    int capacity = 10;
    
    // Constructs a table with initial capacity of ten (ArrayList default)
    public HashTable() {
        table = new ArrayList<LinkedList<WordNode>>();
        for (int i = 0; i < capacity; i++)          // Add each list to the table
            table.add(new LinkedList<WordNode>());  // to ensure a correct index maximum boundary
    }
    
    // Constructs a table with specified initial capacity
    public HashTable(int initialCapacity) {
        if (initialCapacity < 1)
            capacity = 1;
        else
            capacity = initialCapacity;
        table = new ArrayList<LinkedList<WordNode>>(capacity);
        for (int i = 0; i < capacity; i++)          // Add each list to the table
            table.add(new LinkedList<WordNode>());  // to ensure a correct index maximum boundary
    }
    
    // Stores a word-count pair in the table
    // Replaces the count associated with the word if the word already exists
    // Handles collisions with separate chaining
    public void put(String word, int count) {
        WordNode entry = new WordNode(word, count);
        if (this.get(word) == null)
            table.get(Math.abs(word.hashCode() % capacity)).add(entry);
        else
            update(word, count);
    }
    
    // Adds the word-count pair to the table if it does not already exist
    // Otherwise replaces the count of the entry whose word matches the given
    public void update(String word, int count) {
        LinkedList<WordNode> chain = table.get(Math.abs(word.hashCode() % capacity));
        for (WordNode entry : chain) {
            if (entry.getWord().equals(word)) {
                entry.setCount(count);
                return;
            }
        }
        put(word, count);
    }
    
    // Using 1 as the initial count, adds the word to the table if it does not already exist
    // Otherwise increments the count of the entry whose word matches the given
    public void increment(String word) {
        LinkedList<WordNode> chain = table.get(Math.abs(word.hashCode() % capacity));
        for (WordNode entry : chain) {
            if (entry.getWord().equals(word)) {
                entry.setCount(entry.getCount() + 1);
                return;
            }
        }
        put(word, 1);
    }
    
    // Returns the WordNode that contains a specified word or null if no such word exists
    public WordNode get(String word) {
        LinkedList<WordNode> chain = table.get(Math.abs(word.hashCode() % capacity));
        for (WordNode node : chain) {
            if (node.getWord().equals(word))
                return node;
        }
        return null;
    }
    
    // Returns the number of words in the table
    public int size() {
        int size = 0;
        for (LinkedList<WordNode> chain : table)
            size += chain.size();
        return size;
    }
    
    // Simply for convenience, enables iteration over the HashTable
    // The lists that the iterator returns are themselves iterable
    public Iterator<LinkedList<WordNode>> iterator() {
        Iterator<LinkedList<WordNode>> itr = table.iterator();
        return itr;
    }
}