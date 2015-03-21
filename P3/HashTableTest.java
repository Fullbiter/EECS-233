/**
 * HashTableTest.java
 *
 * Kevin Nash (kjn33)
 * 11/11/2014
 */
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class HashTableTest {
    
    @Test
    public void put() {
        HashTable table = new HashTable();
        table.put("Sierra", 117);
        assertEquals(117, table.get("Sierra"));
    }
    
    @Test
    public void putExisting() {
        HashTable table = new HashTable();
        table.put("Sierra", 117);
        table.put("Sierra", 51);
        assertEquals(51, table.get("Sierra"));
    }
    
    @Test
    public void putCollision() {
        HashTable table = new HashTable();
        table.put("Kevin", 1); // 9th bucket
        table.put("Kate", 2);  // 9th bucket
        assertEquals(1, table.get("Kevin"));
        assertEquals(2, table.get("Kate"));
    }
    
    @Test
    public void updateNew() {
        HashTable table = new HashTable();
        table.update("Sierra", 117);
        assertEquals(117, table.get("Sierra"));
    }
    
    @Test
    public void updateExisting() {
        HashTable table = new HashTable();
        table.put("Sierra", 117);
        table.update("Sierra", 51);
        assertEquals(51, table.get("Sierra"));
    }
}