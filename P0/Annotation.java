/**
 * Annotation.java
 *
 * Kevin Nash (kjn33)
 * 09/08/2014
 */

public class Annotation {
    
    int n;
    
    // Assumes n is zero when unspecified
    public Annotation() {
        n = 0;
    }
    
    public Annotation(int n_given) {
        n = n_given;
    }
    
    public int getn() {
        return n;
    }
    
    // Annotates n, using "Fizz" and "Buzz" when appropriate
    public String toString() {
        String n_string = Integer.toString(n);
        if(n % 3 == 0) {
            n_string = "Fizz";
            if(n % 5 == 0)
                n_string += "Buzz";
            return n_string;
        }
        else if(n % 5 == 0)
            return "Buzz";
        return Integer.toString(n);
    }
    
    // Annotates each integer from start to end (inclusive) and
    // returns a string of all the annotations separated by spaces
    public static String annotateList(int start, int end) {
        String output = "";
        for(int i = start; i < end; i++) {
            Annotation newAnnotation = new Annotation(i);
            output += newAnnotation.toString();
            output += " ";
        }
        Annotation endAnnotation = new Annotation(end);
        output += endAnnotation.toString();
        return output;
    }
}