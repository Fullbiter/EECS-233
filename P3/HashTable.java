/**
 * HashTable.java
 *
 * Kevin Nash (kjn33)
 * 11/05/2014
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable implements Iterable<LinkedList<HashEntry>> {
    
    private ArrayList<LinkedList<HashEntry>> table;
    int capacity = 10;
    
    // Constructs a table with initial capacity of ten (ArrayList default)
    public HashTable() {
        table = new ArrayList<LinkedList<HashEntry>>();
        for (int i = 0; i < capacity; i++)          // Add each list to the table
            table.add(new LinkedList<HashEntry>()); // to ensure a correct index maximum boundary
    }
    
    // Constructs a table with specified initial capacity
    public HashTable(int initialCapacity) {
        if (initialCapacity < 1)
            capacity = 1;
        else
            capacity = initialCapacity;
        table = new ArrayList<LinkedList<HashEntry>>(capacity);
        for (int i = 0; i < capacity; i++)          // Add each list to the table
            table.add(new LinkedList<HashEntry>()); // to ensure a correct index maximum boundary
    }
    
    // Stores a key-value pair in the table
    // Replaces the value associated with the key if the key already exists
    // Handles collisions with separate chaining
    public void put(String key, int value) {
        HashEntry entry = new HashEntry(key, value);
        if (this.get(key) == -1)
            table.get(Math.abs(key.hashCode() % capacity)).add(entry);
        else
            update(key, value);
    }
    
    // Provides the same function as above but
    // Overrides the key's hash value for testing purposes
    public void put(String key, int value, int hashCode) {
        HashEntry entry = new HashEntry(key, value);
        if (this.get(key) == -1)
            table.get(Math.abs(hashCode % capacity)).add(entry);
        else
            update(key, value);
    }
    
    // Adds the key-value pair to the table if it does not already exist
    // Otherwise replaces the value of the entry whose key matches the given
    public void update(String key, int value) {
        LinkedList<HashEntry> chain = table.get(Math.abs(key.hashCode() % capacity));
        for (HashEntry entry : chain) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        put(key, value);
    }
    
    // Using 1 as the initial value, adds the key to the table if it does not already exist
    // Otherwise increments the value of the entry whose key matches the given
    public void increment(String key) {
        LinkedList<HashEntry> chain = table.get(Math.abs(key.hashCode() % capacity));
        for (HashEntry entry : chain) {
            if (entry.getKey().equals(key)) {
                entry.setValue(entry.getValue() + 1);
                return;
            }
        }
        put(key, 1);
    }
    
    // Returns the value of a specified key or -1 if no such key exists
    public int get(String key) {
        LinkedList<HashEntry> chain = table.get(Math.abs(key.hashCode() % capacity));
        for (HashEntry entry : chain) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return -1;
    }
    
    // Provides the same function as above but
    // Overrides the key's hash value for testing purposes
    public int get(String key, int hashCode) {
        LinkedList<HashEntry> chain = table.get(Math.abs(hashCode % capacity));
        for (HashEntry entry : chain) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return -1;
    }
    
    // Prints the contents of the table for testing purposes
    public void print() {
        int i = 0;
        for (LinkedList<HashEntry> list : table) {
            System.out.print(i++ + ": ");
            for (HashEntry entry : list)
                System.out.print("[" + entry.getKey() + ", " + entry.getValue() + "] ");
            System.out.println();
        }
    }
    
    // Simply for convenience, enables iteration over the HashTable
    // The lists that the iterator returns are themselves iterable
    public Iterator<LinkedList<HashEntry>> iterator() {
        Iterator<LinkedList<HashEntry>> itr = table.iterator();
        return itr;
    }
}