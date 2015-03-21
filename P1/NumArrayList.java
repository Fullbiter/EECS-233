/**
 * NumArrayList.java
 *
 * Kevin Nash (kjn33)
 * 09/16/2014
 */
public class NumArrayList extends NumList {
    
    private double[] seq = new double[0];
    
    public NumArrayList() {
        double[] seq = new double[0]; // Instantiate an empty aray
    }
    
    public NumArrayList(int size) {
        seq = new double[size]; // Instantiate an array of size i
    }
    
    public int size() {
        return seq.length;
    }
    
    public int capacity() {
        return size();
    }
    
    public double[] getSeq() {
        return seq;
    }
    
    public void add(double value) {
        insert(size(), value);
    }
    
    public void insert(int i, double value) {
        if (i < 0)
            i = 0;
        if (i > size())
            i = size();
        double[] seqTemp = new double[size() + 1];
        for (int j = 0; j < size(); j++) {
            seqTemp[j] = getSeq()[j];
        }
        for (int k = seqTemp.length - 1; k >= i && k > 0; k--) {
            seqTemp[k] = seqTemp[k - 1];
        }
        seqTemp[i] = value;
        seq = seqTemp;
    }
    
    public void remove(int i) {
        if (i >= 0 && i < size()) {
            double[] seqTemp = new double[size() - 1];
            for (int j = 0; j < i; j++) {
                seqTemp[j] = getSeq()[j];
            }
            for (int k = size() - 1; k > i; k--) {
                seqTemp[k - 1] = getSeq()[k];
            }
            seq = seqTemp;
        }
    }
    
    public boolean contains(double value) {
        for (int i = 0; i < size(); i++) {
            if (getSeq()[i] == value)
                return true;
        }
        return false;
    }
    
    public double lookup(int i) {
        if (i >= 0 && i < size()) { // Only attempt to access an index if the passed index is valid
            return getSeq()[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }
    
    public boolean equals(NumList otherList) {
        String myString = this.toString();
        String otherString = otherList.toString();
        return myString.equals(otherString);
    }
    
    public void removeDuplicates() {
        NumArrayList dups = new NumArrayList();
        for (int i = size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (getSeq()[i] == getSeq()[j]) {
                    dups.insert(dups.size(), i);
                    break;
                }
            }
        }
        for (int k = 0; k < dups.size(); k++) {
            remove((int)dups.lookup(k));
        }
    }
    
    public String toString() {
        if (size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (double d : seq) {
            if (sb.length() != 0)
                sb.append(" ");
            sb.append(d);
        }
        return sb.toString();
    }
}