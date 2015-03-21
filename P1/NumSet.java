/**
 * NumSet.java
 *
 * Kevin Nash (kjn33)
 * 09/27/2014
 */
public class NumSet {
    
    private double[] list = new double[0];
        
    public NumSet() {
    }
    
    public NumSet(double[] arr) {
        list = arr;
    }
    
    public int size() {
        return list.length - countDuplicates();
    }
    
    public int sizeWithDuplicates() {
        return list.length;
    }
    
    public boolean contains(double value) {
        for (int i = 0; i < size(); i++) {
            if (getList()[i] == value)
                return true;
        }
        return false;
    }
    
    public String toString() {
        if (size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (double d : list) {
            if (sb.length() != 0)
                sb.append(" ");
            sb.append(d);
        }
        return sb.toString();
    }
    
    public static NumSet intersect(NumSet s1, NumSet s2) {
        if (s1.size() == 0 || s2.size() == 0)
            return new NumSet(new double[0]);
        NumSet s3 = new NumSet();        
        for(int i = 0; i < s1.size(); i++) {
            if(s2.contains(s1.lookup(i)))
                s3.add(s1.lookup(i));        
        }
        return s3;
    }
    
    public static NumSet union(NumSet s1, NumSet s2) {        
        NumSet s3 = new NumSet();
        for (int i = 0; i < s1.size(); i++)
            s3.add(s1.lookup(i));
        for (int j = 0; j < s2.size(); j++) {
            if (!s1.contains(s2.lookup(j)))
                s3.add(s2.lookup(j));
        }
        return s3;
    }
    
    public double lookup(int i) {
        if (i >= 0 && i < size()) { // Only attempt to access an index if the passed index is valid
            return getList()[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }
    
    public double[] getList() {
        return list;
    }
    
    public void add(double value) {
        insert(size(), value);
    }
    
    public void insert(int i, double value) {
        if (i < 0)
            i = 0;
        if (i > size())
            i = size();
        double[] listTemp = new double[size() + 1];
        for (int j = 0; j < size(); j++) {
            listTemp[j] = getList()[j];
        }
        for (int k = listTemp.length - 1; k >= i && k > 0; k--) {
            listTemp[k] = listTemp[k - 1];
        }
        listTemp[i] = value;
        list = listTemp;
    }
    
    public static boolean equivalence(NumSet s1, NumSet s2) {
        if (s1.size() == s2.size()) {
            double sum1 = 0;
            double sum2 = 0;
            double prod1 = 1;
            double prod2 = 1;
            for(int i = 0; i < s1.size(); i++) {
                sum1 += s1.lookup(i);
                if (s1.lookup(i) != 0)
                    prod1 *= s1.lookup(i);
            }
            for(int i = 0; i < s1.size(); i++) {
                sum2 += s2.lookup(i);
                if (s2.lookup(i) != 0)
                    prod2 *= s2.lookup(i);
            }
            if (sum1 == sum2 && prod1 == prod2)
                return true;
        }
        return false;
    }
    
    public void remove(int i) {
        if (i >= 0 && i < size()) {
            double[] seqTemp = new double[size() - 1];
            for (int j = 0; j < i; j++) {
                seqTemp[j] = getList()[j];
            }
            for (int k = size() - 1; k > i; k--) {
                seqTemp[k - 1] = getList()[k];
            }
            list = seqTemp;
        }
    }
    
    public int countDuplicates() {
        NumArrayList dups = new NumArrayList();
        for (int i = this.sizeWithDuplicates() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (getList()[i] == getList()[j]) {
                    dups.insert(dups.size(), i);
                    break;
                }
            }
        }
        return dups.size();
    }
}