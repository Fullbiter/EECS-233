/**
 * NumLinkedList.java
 *
 * Kevin Nash (kjn33)
 * 09/18/2014
 */
public class NumLinkedList extends NumList {
    
    private Node head = null;
    private Node tail = head;
    
    private static class Node {
        double data;
        Node next;
        
        public Node() {
            data = 0.0;
            next = null;
        }
        
        public Node(double d, Node n) {
            data = d;
            next = n;
        }
    }
    
    public int size() {
        if (head == null)
            return 0;
        Node trav = head;
        int size = 1;
        while (trav.next != null) {
            trav = trav.next;
            size++;
        }
        return size;
    }
    
    public void add(double value) {
        if (size() == 0) {
            head = new Node(value, tail);
            tail = head;
        }
        else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }
    
    public void insert(int i, double value) {
        if (i < 0)
            i = 0;
        if (i < size()) {
            if (i == 0) {
                Node newnode = new Node(value, head);
                head = newnode;
            }
            else {
                Node trav = head;
                for (int j = 0; j < i - 1; j++)
                    trav = trav.next;
                Node newNode = new Node(value, trav.next);
                if (trav.next == null) {
                    tail = newNode;
                }
                else
                    trav.next = newNode;
            }
        }
        else
            add(value);
    }
    
    public void remove(int i) {
        if (i < 0 || i > size() - 1)
            return;
        Node trav = head;
        if (i == 0) {
            if (trav.next == null)
                head = null;
            else
                head = head.next;
        }
        else {
            for (int j = 1; j < i; j++) {
                trav = trav.next;
            }
            if (trav.next.next == null) {
                trav.next = null;
                tail = trav;
            }
            else
                trav.next = trav.next.next;
        }
    }
    
    public boolean contains(double value) {
        Node trav = head;
        for (int i = 0; i < size(); i++) {
            if (trav.data == value)
                return true;
            if (trav.next != null)
                trav = trav.next;
        }
        return false;
    }
    
    public double lookup(int i) {
        Node trav = head;
        if (i < 0 || i > size() - 1)
            throw new IndexOutOfBoundsException();
        for (int j = 0; j < i; j++)
            trav = trav.next;
        return trav.data;
    }
    
    public boolean equals(NumList otherList) {
        String myString = toString();
        String otherString = otherList.toString();
        return myString.equals(otherString);
    }
    
    public void removeDuplicates() {
        if (head == null || head.next == null)
            return;
        
        Node travUnique = head;
        int travUniqueIndex = 0;
        Node travAll = head.next;
        int travAllIndex = 1;
        
        while (travUniqueIndex < size() - 1) {
            while (travAllIndex < size()) {
                if (travAll.data == travUnique.data)
                    remove(travAllIndex);
                if (travAll.next == null)
                    break;
                travAll = travAll.next;
                travAllIndex++;
            }
            if (travUniqueIndex < size() - 1) {
                travUniqueIndex++;
                travUnique = travUnique.next;
                travAllIndex = travUniqueIndex + 1;
                travAll = travUnique.next;
            }
        }
    }
    
    public String toString() {
        if (size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        Node trav = head;
        for (int i = 0; i < size(); i++) {
            if (sb.length() != 0)
                sb.append(" ");
            sb.append(trav.data);
            trav = trav.next;
        }
        return sb.toString();
    }
}