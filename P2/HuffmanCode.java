/**
 * HuffmanCode.java
 *
 * Kevin Nash (kjn33)
 * 10/18/2014
 */
import java.util.*;
public class HuffmanCode {
    
    private LinkedList<HuffmanNode> tree = new LinkedList<HuffmanNode>();
    private LinkedList<HuffmanNode> leafList = new LinkedList<HuffmanNode>();
    
    // Instantiates fields and builds a tree
    public HuffmanCode(byte[] b) {
        ByteCounter bc = new ByteCounter(b);
        bc.setOrder("countDec");
        leafList = bc.getElementLinkedList();
        tree = duplicateList(leafList);
        buildTree();
    }
    
    // Instantiates fields and builds a tree by reading bytes from a specified file
    public HuffmanCode(String fileName) throws java.io.IOException {
        ByteCounter bc = new ByteCounter(fileName);
        bc.setOrder("countDec");
        leafList = bc.getElementLinkedList();
        tree = duplicateList(leafList);
        buildTree();
    }
    
    // Instantiates fields and builds a tree
    // Input order is preserved
    public HuffmanCode(byte[] b, int[] c) {
        for (int x : c) {
            if (x < 0) // counts cannot be negative
                throw new IllegalArgumentException();
        }
        ByteCounter bc = new ByteCounter(b);
        if (bc.getElements().length != b.length) // bytes must be unique
            throw new IllegalArgumentException();
        LinkedList<HuffmanNode> tempList = new LinkedList<HuffmanNode>();
        for (int i = 0; i < b.length; i++)
            tempList.add(new HuffmanNode(b[i], c[i]));
        leafList = tempList;  
        tree = duplicateList(leafList);
        buildTree();
    }
    
    // Returns the code of a byte -- byte must exist
    public boolean[] code(byte b) {
        for (HuffmanNode n : leafList) {
            if (n.getValue() == b)
                return n.getCode();
        }
        throw new IllegalArgumentException();
    }
    
    // Implements code() but converts booleans to a String of ones and zeroes
    public String codeString(byte b) {
        StringBuilder sb = new StringBuilder();
        boolean[] codeArray = code(b);
        for (int i = 0; i < codeArray.length; i++) {
            if (codeArray[i])
                sb.append("1");
            else
                sb.append("0");
        }
        return sb.toString();
    }
    
    // Returns a string of elements in format "byte: code" with line breaks
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leafList.size(); i++) {
            sb.append(leafList.get(i).getValue() + ": " + codeString(leafList.get(i).getValue()));
            if (i < leafList.size() - 1)
                sb.append("\n");
        }
        return sb.toString();
    }
    
    // Helper method used to assign a code to a byte using recursive traversal
    public void determineCode(HuffmanNode node, ArrayList<Boolean> code) {
        node.setCode(convertCode(code));
        if (node.getLeft() == null && node.getRight() == null) {
            for (int i = 0; i < leafList.size(); i++) {
                if (node.getValue() == leafList.get(i).getValue())
                    leafList.get(i).setCode(convertCode(code));
            }
        }
        else {
            ArrayList<Boolean> leftCode = new ArrayList<Boolean>(code);
            ArrayList<Boolean> rightCode = new ArrayList<Boolean>(code);
            leftCode.add(false);
            rightCode.add(true);
            determineCode(node.getLeft(), leftCode);
            determineCode(node.getRight(), rightCode);
        }
    }
    
    // Helper method used to convert a List (of objects) to an array (of primitives)
    public boolean[] convertCode(ArrayList<Boolean> codeList) {
        boolean[] codeArray = new boolean[codeList.size()];
        for (int i = 0; i < codeArray.length; i++)
            codeArray[i] = codeList.get(i);
        return codeArray;
    }
    
    // Helper method used to build a tree, called from constructor
    public void buildTree() {
        if (tree.size() < 2)
            throw new IllegalArgumentException();
        while (tree.size() >= 2) {
            tree.addLast(mergeNodes(tree.poll(), tree.poll())); // removes the first two elements, adds the merged result
            Collections.sort(tree, HuffmanNode.incComparator); // sort list in increasing order
        }
        determineCode(tree.getFirst(), new ArrayList<Boolean>());
    }
    
    // Helper method used to merge two nodes into a parent of those nodes -- combines counts
    public HuffmanNode mergeNodes(HuffmanNode one, HuffmanNode two) {
        int sumCount = one.getCount() + two.getCount();
        HuffmanNode merged = new HuffmanNode((byte) -1, sumCount);
        merged.setLeft(one);
        merged.setRight(two);
        return merged;
    }
    
    // Helper method used to create a deep copy of a List
    public LinkedList<HuffmanNode> duplicateList(LinkedList<HuffmanNode> one) {
        LinkedList<HuffmanNode> two = new LinkedList<HuffmanNode>();
        for (HuffmanNode n : one)
            two.add(n);
        return two;
    }
}