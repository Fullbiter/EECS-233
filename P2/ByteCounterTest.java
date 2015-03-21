import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ByteCounterTest {
    
    @Test
    public void testByteArrayConstructor() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        ByteCounter bc = new ByteCounter(allBytes);
    }
    
    @Test
    public void testFilenameConstructor() throws IOException {
        ByteCounter bc = new ByteCounter("file.txt");
    }
    
    @Test
    public void testZeroByteGetCount() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        byte b = 105;
        ByteCounter bc = new ByteCounter(allBytes);
        assertEquals("getCount should count no occurences for a byte that does not exist in the byte array.", 
                     0, bc.getCount(b));
    }
    
    @Test
    public void testByteGetCount() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        byte b = 111;
        ByteCounter bc = new ByteCounter(allBytes);
        assertEquals("getCount should count the number of occurences for a given byte.", 
                     2, bc.getCount(b));
    }
    
    @Test
    public void testByteArrayGetCount() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        byte[] elements = {32,100,101,104,108,111,114,119};
        ByteCounter bc = new ByteCounter(allBytes);
        assertArrayEquals("getCount should count the occurence of every byte in a given array.", 
                          new int[]{1,1,1,1,3,2,1,1}, bc.getCount(elements));
    }
    
    @Test
    public void testGetElements() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        byte[] elements = {32,100,101,104,108,111,114,119};
        ByteCounter bc = new ByteCounter(allBytes);
        assertArrayEquals("getElements should return only unique bytes",
                          elements, bc.getElements());
    }
        
    @Test (expected = IllegalArgumentException.class)
    public void testSortInvalidOrder() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        ByteCounter bc = new ByteCounter(allBytes);
        bc.setOrder("INVALID");
    }
        
    @Test
    public void testSortByteOrder() {
        byte[] allBytes = {100,101,104,108,32,111,114,119};
        ByteCounter bc = new ByteCounter(allBytes);
        String model = "32:1 100:1 101:1 104:1 108:1 111:1 114:1 119:1";
        bc.setOrder("byte");
        assertEquals("\"byte\" should sort from least-to-greatest byte value",
                          model, bc.toString());
    }
    
    @Test
    public void testSortDecOrder() {
        byte[] allBytes = {32,100,101,104,108,108,111,114,119};
        ByteCounter bc = new ByteCounter(allBytes);
        String model = "108:2 32:1 100:1 101:1 104:1 111:1 114:1 119:1";
        bc.setOrder("countDec");
        assertEquals("\"countDec\" should sort from greatest-to-least count value",
                          model, bc.toString());
    }
    
    @Test
    public void testSortIncOrder() {
        byte[] allBytes = {32,100,101,104,108,108,111,114,119};
        ByteCounter bc = new ByteCounter(allBytes);
        String model = "32:1 100:1 101:1 104:1 111:1 114:1 119:1 108:2";
        bc.setOrder("countInc");
        assertEquals("\"countInc\" should sort from least-to-greatest count value",
                          model, bc.toString());
    }
    
    @Test
    public void testCharToString() {
        byte[] allBytes = {32,100,101,104,108,111,114,119};
        ByteCounter bc = new ByteCounter(allBytes);
        String model = " :1 d:1 e:1 h:1 l:1 o:1 r:1 w:1";
        bc.setOrder("byte");
        assertEquals("toString(\"char\") should convert byte elements into char form",
                          model, bc.toString("char"));
    }
}