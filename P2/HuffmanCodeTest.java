import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class HuffmanCodeTest {
    
    @Test
    public void testByteArrayConstructor() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        HuffmanCode hc = new HuffmanCode(allBytes);
    }
    
    @Test
    public void testFilenameConstructor() throws IOException {
        HuffmanCode hc = new HuffmanCode("file.txt");
    }
    
    @Test
    public void testByteAndCountArrayConstructor() {
        byte[] allBytes = {32,100,101,104,108,111,114,119};
        int[] counts = {1,1,1,1,3,2,1,1};
        HuffmanCode hc = new HuffmanCode(allBytes, counts);
    }
    
    @Test
    public void testCodeValid() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        HuffmanCode hc = new HuffmanCode(allBytes);
        boolean[] model = {true, true, false, false};
        assertTrue("code should provide the code for a given valid byte",
                   Arrays.equals(model, hc.code((byte)32)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCodeInvalid() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        HuffmanCode hc = new HuffmanCode(allBytes);
        hc.code((byte)30);
    }
    
    @Test
    public void testCodeString() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        HuffmanCode hc = new HuffmanCode(allBytes);
        String model = "1100";
        assertEquals("codeString should provide the code for a given byte in String form",
                     model, hc.codeString((byte)32));
    }
    
    @Test
    public void testToString() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        HuffmanCode hc = new HuffmanCode(allBytes);
        String model = "108: 00\n111: 01\n32: 1100\n100: 1101\n101: 1110\n104: 1111\n114: 100\n119: 101";
        assertEquals("codeString should provide the code for a given byte in String form",
                     model, hc.toString());
    }
}