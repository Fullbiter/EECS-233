import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanListTest {
    
    @Test
    public void testByteArrayConstructor() {
        byte[] allBytes = {104,101,108,108,111,32,119,111,114,108,100};
        HuffmanList hl = new HuffmanList(allBytes);
    }
    
    @Test
    public void testFilenameConstructor() throws IOException {
        HuffmanList hl = new HuffmanList("file.txt");
    }
    
    @Test
    public void testByteAndCountArrayConstructor() {
        byte[] allBytes = {32,100,101,104,108,111,114,119};
        int[] counts = {1,1,1,1,3,2,1,1};
        HuffmanList hl = new HuffmanList(allBytes, counts);
    }
}