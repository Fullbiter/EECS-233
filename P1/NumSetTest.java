/**
 * NumSetTest.java
 *
 * Kevin Nash (kjn33)
 * 09/27/2014
 */
import static org.junit.Assert.*;
import org.junit.Test;
public class NumSetTest {
    
    @Test
    public void testConstructor() {
        NumSet testSet = new NumSet(new double[] {0.0, 1.0, 2.0, 3.0});
        
        assertEquals("Constructor should initialize a set with the passed values.",
                     "0.0 1.0 2.0 3.0", testSet.toString());
    }
    
    @Test
    public void testSize() {
        NumSet testSet = new NumSet(new double[] {0.0, 1.0, 2.0, 2.0});
        
        assertEquals("The size of a list with 3 items should be 3.",
                     3, testSet.size());
    }
    
    @Test
    public void testUnion() {
        NumSet testS1 = new NumSet(new double[] {0.0, 1.0, 2.0, 3.0});
        NumSet testS2 = new NumSet(new double[] {3.0, 4.0, 5.0, 6.0});
                
        assertEquals("Union should combine two sets without duplicates.",
                     "0.0 1.0 2.0 3.0 4.0 5.0 6.0", NumSet.union(testS1, testS2).toString());
        
        NumSet testS3 = new NumSet(new double[] {});
               
        assertEquals("Union should combine two sets without duplicates.",
                     "0.0 1.0 2.0 3.0", NumSet.union(testS1, testS3).toString());
    }
    
    @Test
    public void testIntersect() {
        NumSet testS1 = new NumSet(new double[] {0.0, 1.0, 2.0, 3.0});
        NumSet testS2 = new NumSet(new double[] {3.0, 4.0, 5.0, 6.0});
                
        assertEquals("Intersection should combine duplicates of two sets.",
                     "3.0", NumSet.intersect(testS1, testS2).toString());
        
        NumSet testS3 = new NumSet(new double[] {});
               
        assertEquals("Intersection should combine duplicates of two sets.",
                     "", NumSet.intersect(testS1, testS3).toString());
    }
    
    @Test
    public void testEquivalence() {
        NumSet testS1 = new NumSet(new double[] {0.0, 1.0, 2.0, 3.0});
        NumSet testS2 = new NumSet(new double[] {3.0, 4.0, 5.0, 6.0});
        NumSet testS3 = new NumSet(new double[] {0.0, 1.0, 2.0, 3.0});
        NumSet testS4 = new NumSet(new double[] {});
        
        assertFalse("Two sets are not equivalent if members are not shared.",
                     NumSet.equivalence(testS1, testS2));
        
        assertTrue("Two sets are equivalent if also indentical.",
                     NumSet.equivalence(testS1, testS3));
        
        assertFalse("Two sets are not equivalent if one set is empty.",
                     NumSet.equivalence(testS1, testS4));
    }
    
}