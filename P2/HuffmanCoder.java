/**
 * HuffmanCoder.java
 *
 * Kevin Nash (kjn33)
 * 10/18/2014
 */
import java.util.*;
public class HuffmanCoder {
    
    private String inputFile;
    private String outputFile;
    
    // Specifies input and output files
    public HuffmanCoder(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    
    // Calls BinaryWriter to output a compressed form of the input file
    public void compress() throws java.io.IOException {
        ByteCounter bc = new ByteCounter(inputFile);
        HuffmanCode hc = new HuffmanCode(inputFile);
        BinaryWriter writer = new BinaryWriter(outputFile);
        for (int i = 0; i < bc.getAllBytes().length; i++)
            writer.writeBinaryArray(hc.code(bc.getAllBytes()[i]));
        writer.close();
    }        
}