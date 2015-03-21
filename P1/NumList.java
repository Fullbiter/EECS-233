/**
 * NumList.java
 *
 * Kevin Nash (kjn33)
 * 09/18/2014
 */

abstract class NumList {
    
    public abstract int size();
    
    public abstract void add(double value);
    
    public abstract void insert(int i, double value);
    
    public abstract void remove(int i);
    
    public abstract boolean contains(double value);
    
    public abstract double lookup(int i);
    
    public abstract boolean equals(NumList otherList);
    
    public abstract void removeDuplicates();
    
    public abstract String toString();
}
