/**
 * HuffmanList.java
 *
 * Kevin Nash (kjn33)
 * 10/18/2014
 */
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class HuffmanList implements Iterable<HuffmanNode> {
    
    private List<HuffmanNode> list = new LinkedList<HuffmanNode>(); 
    
    public HuffmanList(byte[] b) {
        ByteCounter bc = new ByteCounter(b);
        bc.setOrder("countInc");
        list = bc.getElementList();
    }
    
    public HuffmanList(String fileName) throws java.io.IOException {
        ByteCounter bc = new ByteCounter(fileName);
        bc.setOrder("countInc");
        list = bc.getElementList();
    }
    
    // Input order is preserved
    public HuffmanList(byte[] b, int[] c) {
        for (int x : c) {
            if (x < 0) // counts cannot be negative
                throw new IllegalArgumentException();
        }
        ByteCounter bc = new ByteCounter(b);
        if (bc.getElements().length != b.length) // bytes must be unique
            throw new IllegalArgumentException();
        List<HuffmanNode> tempList = new LinkedList<HuffmanNode>();
        for (int i = 0; i < b.length; i++)
            tempList.add(new HuffmanNode(b[i], c[i]));
        list = tempList;
    }
    
    public List<HuffmanNode> getList() {
        return list;
    }
    
    // Implements Java's iterator
    public Iterator<HuffmanNode> iterator() {
        return list.iterator();
    }
}