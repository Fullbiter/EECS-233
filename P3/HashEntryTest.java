/**
 * HashEntryTest.java
 *
 * Kevin Nash (kjn33)
 * 11/11/2014
 */
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class HashEntryTest {
    
    @Test
    public void getKey() {
        HashEntry entry = new HashEntry("Sierra", 117);
        assertEquals("Sierra", entry.getKey());
    }
    
    @Test
    public void getValue() {
        HashEntry entry = new HashEntry("Sierra", 117);
        assertEquals(117, entry.getValue());
    }
    
    @Test
    public void setValue() {
        HashEntry entry = new HashEntry("Sierra", 117);
        entry.setValue(51);
        assertEquals(51, entry.getValue());
    }
}