import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanNodeTest {
    
    @Test
    public void testConstructor() {
        HuffmanNode node = new HuffmanNode((byte)108, 3);
    }
    
    @Test
    public void testGetValue() {
        HuffmanNode node = new HuffmanNode((byte)108, 3);
        assertEquals("getValue should return the byte value of the node",
                     108, node.getValue());
    }
    
    @Test
    public void testGetCount() {
        HuffmanNode node = new HuffmanNode((byte)108, 3);
        assertEquals("getCount should return the count of the node",
                     3, node.getCount());
    }
}