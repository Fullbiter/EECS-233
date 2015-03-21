import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanCoderTest {
        
    @Test
    public void testConstructor() throws IOException{
        HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
    }
    
    @Test
    public void testCompressMethod() throws IOException{
        HuffmanCoder hc = new HuffmanCoder("file.txt", "output.txt");
        hc.compress();
    }    
}