/**
 * ByteCounter.java
 *
 * Kevin Nash (kjn33)
 * 10/13/2014
 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ByteCounter {
    
    private byte[] allBytes = new byte[0]; // The full text in byte form
    private byte[] elements = new byte[0]; // Only unique bytes
    private List<HuffmanNode> elementList = new ArrayList<HuffmanNode>();
    
    public ByteCounter(byte[] b) {
        allBytes = b;
        elements = getElements();
        elementList = getElementList();
    }
    
    // Gathers a byte[] from a specified file
    public ByteCounter(String fileName) throws java.io.IOException {
        allBytes = Files.readAllBytes(Paths.get(fileName));
        elements = getElements();
        elementList = getElementList();
    }
    
    // Returns the full text in byte form
    public byte[] getAllBytes() {
        return allBytes;
    }
    
    // This method counts the occurences of byte b using a linear search O(N)
    public int getCount(byte b) {
        int count = 0;
        for (byte a : allBytes) {
            if (a == b)
                count++;
        }
        return count;
    }
    
    // This search makes a small fraction of N^2 comparisons on average
    public int[] getCount(byte[] b) {
        int[] counts = new int[b.length];
        
        List<Integer> indexes = new ArrayList<Integer>(); // Create an iterable list of indexes
        for (int u = 0; u < allBytes.length; u++)         // that have not been searched
            indexes.add(u);
        Iterator<Integer> iter = indexes.iterator();
        
        for (int i = 0; i < b.length; i++) { // Linear search only over unsearched indexes
            while (iter.hasNext()) {
                int j = iter.next();
                if (b[i] == allBytes[j]) {
                    counts[i]++;
                    iter.remove();
                }
            }
            iter = indexes.iterator();
        }
        return counts;
    }
    
    // Copies all bytes into a Set to remove duplicates, returns the Set as a sorted byte[]
    public byte[] getElements() {
        Set<Byte> tempSet = new TreeSet<Byte>();
        for (Byte b : allBytes)
            tempSet.add(b);
        byte[] elements = new byte[tempSet.size()];
        int e = 0;
        for (Byte b : tempSet)
            elements[e++] = b;
        return elements;
    }
    
    // Converts the byte[] of elements into an ArrayList
    public List<HuffmanNode> getElementList() {
        List<HuffmanNode> tempList = new ArrayList<HuffmanNode>();
        for (byte e : elements)
            tempList.add(new HuffmanNode(e, getCount(e)));
        return tempList;
    }
    
    // Converts the byte[] of elements into an LinkedList
    public LinkedList<HuffmanNode> getElementLinkedList() {
        LinkedList<HuffmanNode> tempList = new LinkedList<HuffmanNode>();
        for (byte e : elements)
            tempList.add(new HuffmanNode(e, getCount(e)));
        return tempList;
    }
    
    // Indicates a change in sorting order -- byte is default
    public void setOrder(String order) {
        if (order.equals("byte") || order.equals("countDec") || order.equals("countInc"))
            sortElements(order);
        else
            throw new IllegalArgumentException();
    }
    
    // Sorts the elements into the specified order
    public void sortElements(String order) {
        if (order.equals("byte")) {
            elements = getElements();
            elementList = getElementList();
        }
        else if (order.equals("countDec")) {
            Collections.sort(elementList, HuffmanNode.decComparator);
            int i = 0;
            for (HuffmanNode n : elementList)
                elements[i++] = n.getValue();
        }
        else if (order.equals("countInc")) {
            Collections.sort(elementList, HuffmanNode.incComparator);
            int i = 0;
            for (HuffmanNode n : elementList)
                elements[i++] = n.getValue();
        }
    }    
    
    // Returns a string of elements in format "byte:count byte:count"
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int[] counts = getCount(elements);
        for (int i = 0; i < elements.length; i++) {
            if (sb.length() != 0)
                sb.append(" ");
            sb.append(elements[i] + ":" + counts[i]);
        }
        return sb.toString();
    }
    
    // Accepts a format change -- can convert bytes into chars
    public String toString(String format) {
        StringBuilder sb = new StringBuilder();
        if (format.equals("char")) {
            int[] counts = getCount(elements);
            for (int i = 0; i < elements.length; i++) {
                if (sb.length() != 0)
                    sb.append(" ");
                sb.append((char)elements[i] + ":" + counts[i]);
            }
        }
        else if (format.equals("byte"))
            return toString();
        else
            throw new IllegalArgumentException();
        return sb.toString();
    }
}