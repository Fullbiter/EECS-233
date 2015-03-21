/**
 * HashEntry.java
 *
 * Kevin Nash (kjn33)
 * 11/05/2014
 */
public class HashEntry {
    
    private String key;
    private int value;
    private int code;
    
    public HashEntry(String key, int value) {
        this.key = key;
        this.value = value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getKey() {
        return key;
    }
}