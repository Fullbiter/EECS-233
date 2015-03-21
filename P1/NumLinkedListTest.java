/**
 * NumLinkedListTest.java
 *
 * Kevin Nash (kjn33)
 * 09/18/2014
 */
import static org.junit.Assert.*;
import org.junit.Test;
public class NumLinkedListTest {
    
    @Test
    public void testConstructor() {
        NumLinkedList testLL = new NumLinkedList();
        
        assertEquals("Constructor should initialize a list of size 0 when no parameter is given.",
                     0, testLL.size());
    }
    
    @Test
    public void testSize() {
        NumLinkedList testLL = new NumLinkedList();
        
        assertEquals("The size of a default list should be 0.",
                     0, testLL.size());
        
        testLL.add(4.0);
        testLL.add(2.0);
        testLL.add(3.0);
        assertEquals("The size of a list with 3 items should be 3.",
                     3, testLL.size());
    }
    
    @Test
    public void testAdd() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.add(1.0);
        assertEquals("An element was not added successfully.",
                     1, testLL.size());
    }
    
    @Test
    public void testInsert() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.add(1.0);
        testLL.add(2.0);
        testLL.add(3.0);
        
        testLL.insert(0, 0.0);
        assertEquals("When given index 0, an element should be added to the front of the sequence.",
                     "0.0 1.0 2.0 3.0", testLL.toString());
        
        testLL.insert(5, 4.0);
        assertEquals("When given an index exceeding the sequence length, an element should be added to the rear.",
                     "0.0 1.0 2.0 3.0 4.0", testLL.toString());
        
        testLL.insert(2, 9.0);
        assertEquals("When given an index in the middle, an element should be inserted at that index.",
                     "0.0 1.0 9.0 2.0 3.0 4.0", testLL.toString());
    }
    
    @Test
    public void testRemove() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.add(1.0);
        testLL.add(2.0);
        testLL.add(3.0);
        testLL.add(4.0);
        
        testLL.remove(0);
        assertEquals("When given index 0, an element should be removed from the front of the sequence.",
                     "2.0 3.0 4.0", testLL.toString());
        
        testLL.remove(1);
        assertEquals("When given an index in the middle, that element should be removed.",
                     "2.0 4.0", testLL.toString());
        
        testLL.remove(1);
        assertEquals("When given the last index, the last element should be removed.",
                     "2.0", testLL.toString());
        
        testLL.remove(9);
        assertEquals("When given an index exceeding the sequence length, no change should occur.",
                     "2.0", testLL.toString());
    }
    
    @Test
    public void testContains() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.add(1.0);
        
        assertEquals("Contains should report true when a specified value exists.",
                     true, testLL.contains(1.0));
        
        assertEquals("Contains should report false when a specified value does not exist.",
                     false, testLL.contains(9.0));
    }
    
    @Test
    public void testLookup() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.add(1.0);
        testLL.add(2.0);
        testLL.add(3.0);
        
        assertEquals("Lookup should return the value at the specified index.",
                     1.0, testLL.lookup(0), 0.01);
        
        assertEquals("Lookup should return the value at the specified index.",
                     2.0, testLL.lookup(1), 0.01);
        
        assertEquals("Lookup should return the value at the specified index.",
                     3.0, testLL.lookup(2), 0.01);
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void testInvalidLookup() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.lookup(0);
    }
            
    @Test
    public void testEquals() {
        NumLinkedList testLL1 = new NumLinkedList();
        NumLinkedList testLL2 = new NumLinkedList();
        NumLinkedList testLL3 = new NumLinkedList();
        
        assertTrue("ArrayLists are equal when they are both empty.",
                     testLL1.equals(testLL2));
                   
        testLL1.add(1.0);
        testLL1.add(2.0);
        testLL2.add(1.0);
        testLL2.add(2.0);
        assertTrue("ArrayLists are equal when they have the same elements and the same order.",
                     testLL1.equals(testLL2));
            
        testLL2.add(3.0);
        testLL2.add(4.0);
        assertFalse("ArrayLists are not equal when they have unique elements.",
                     testLL1.equals(testLL2));
                
        testLL3.add(2.0);
        testLL3.add(1.0);
        assertFalse("ArrayLists are not equal when their orders differ.",
                     testLL1.equals(testLL3));
    }

    @Test
    public void testRemoveDuplicates() {
        NumLinkedList testLL = new NumLinkedList();
        testLL.add(1.0);
        testLL.add(2.0);
        testLL.add(3.0);
        
        testLL.removeDuplicates();
        assertEquals("No change should occur when no duplicates are present.",
                     "1.0 2.0 3.0", testLL.toString());
        
        testLL.add(2.0);
        testLL.removeDuplicates();
        assertEquals("No duplicates should be present after the removeDuplicates operation.",
                     "1.0 2.0 3.0", testLL.toString());
        
        testLL.add(1.0);
        testLL.add(3.0);
        testLL.removeDuplicates();
        assertEquals("No duplicates should be present after the removeDuplicates operation.",
                     "1.0 2.0 3.0", testLL.toString());
    }
    
    @Test
    public void testToString() {
        NumLinkedList testLL = new NumLinkedList();
        
        assertEquals("The string should be empty when no elements are present.",
                     "", testLL.toString());
        
        testLL.add(1.0);
        assertEquals("The string should contain all elements of the sequence.",
                     "1.0", testLL.toString());
    }
}