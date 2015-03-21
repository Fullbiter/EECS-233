/**
 * HuffmanNode.java
 *
 * Kevin Nash (kjn33)
 * 10/18/2014
 */
import java.util.Comparator;

public class HuffmanNode implements Comparable<HuffmanNode> {
    
    public byte b;
    public int count;
    public boolean[] code;
    // public HuffmanNode next; // Unused 
    public HuffmanNode left;
    public HuffmanNode right;
    
    public HuffmanNode(byte b, int c) {
        this.b = b;
        count = c;
        left = null;
        right = null;
    }
    
    public void setValue(byte b) {
        this.b = b;
    }
    
    public byte getValue() {
        return b;
    }
    
    public void setCount(int c) {
        count = c;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCode(boolean[] c) {
        code = c;
    }
    
    public boolean[] getCode() {
        return code;
    }
    
    public void setLeft(HuffmanNode l) {
        left = l;
    }
    
    public HuffmanNode getLeft() {
        return left;
    }
    
    public void setRight(HuffmanNode r) {
        right = r;
    }
    
    public HuffmanNode getRight() {
        return right;
    }
    
    // Provides criteria for comparing byte values -- natural order
    public int compareTo(HuffmanNode other) {
        return this.getValue() - other.getValue();
    }
    
    // Provides criteria for comparing counts -- high to low
    public static Comparator<HuffmanNode> decComparator = new Comparator<HuffmanNode>() {
        public int compare(HuffmanNode one, HuffmanNode two) {
            Integer countOne = one.getCount();
            Integer countTwo = two.getCount();
            return countTwo.compareTo(countOne);            
        }
    };
    
    // Provides criteria for comparing counts -- low to high
    public static Comparator<HuffmanNode> incComparator = new Comparator<HuffmanNode>() {
        public int compare(HuffmanNode one, HuffmanNode two) {
            Integer countOne = one.getCount();
            Integer countTwo = two.getCount();
            return countOne.compareTo(countTwo);            
        }   
    };
}