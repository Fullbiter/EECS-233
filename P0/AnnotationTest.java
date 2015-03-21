import static org.junit.Assert.*;
import org.junit.Test;

public class AnnotationTest {
  
  /* JUnit tests for constructors */
  @Test
  public void testNullConstructor() {
    Annotation n = new Annotation();
    assertEquals("A constructor with no parameters should assume n is 0.",
                 0, n.getn());
  } 
  @Test
  public void testIntegerConstructor() {
    Annotation n = new Annotation(1);
    assertEquals("A constructor with one integer parameter should store the value of that number.",
                 1, n.getn());
  }
  
  /* JUnit tests for the toString method */
  @Test
  public void testToStringInteger() {
    Annotation n = new Annotation(1);
    assertEquals("An n value that is neither divisible by 3 nor 5 should return n as a String.",
                 "1", n.toString());
  }
  @Test
  public void testToStringFizz() {
    Annotation n = new Annotation(3);
    assertEquals("An n value that is divisible only by 3 should return \"Fizz\".",
                 "Fizz", n.toString());
  }
  @Test
  public void testToStringBuzz() {
    Annotation n = new Annotation(5);
    assertEquals("An n value that is divisible only by 5 should return \"Buzz\".",
                 "Buzz", n.toString());
  }
  @Test
  public void testToStringFizzBuzz() {
    Annotation n = new Annotation(15);
    assertEquals("An n value that is divisible by 3 and 5 should return \"FizzBuzz\".",
                 "FizzBuzz", n.toString());
  }
  
  /* JUnit tests for the annotateList method */
  @Test
  public void testAnnotationListOne() {
    assertEquals("A passed range of numbers should follow the annotation criteria for one item.",
                 "1", Annotation.annotateList(1,1));
  }
  @Test
  public void testAnnotationListMany() {
    assertEquals("A passed range of numbers should follow the annotation criteria for many items.",
                 "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16", Annotation.annotateList(1,16));
  }
}