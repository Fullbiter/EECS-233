import static org.junit.Assert.*;

import org.junit.Test;

public class NumListTest {

	/* 
	 * JUnit tests of fundamental functionality.
	 *   
	 * Use these JUnit tests to ensure that your code compiles
	 * and correctly implements the fundamental functionality.
	 * 
	 * Code that does not pass these tests will not be graded.
	 * Your draft submission needs to pass these tests in order
	 * to receive a non-zero grade on the assignment.
	 */

	// Tests for class NumArrayList's fundamental functions
	@Test
	public void testNumArrayListConstructorAndToString() {
		NumArrayList list = new NumArrayList();		
		assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
				"It also can be the problem in method TOSTRING.", 
				"", list.toString());
	}

	@Test
	public void testNumArrayListAddAndToString() {
		NumArrayList list = new NumArrayList();

		list.add(1.0);
		list.add(3.0);
		list.add(2.0);

		assertEquals("Add method should add element to the end of list each time. " +
				"It's also can be the problem in method TOSTRING.",
				"1.0 3.0 2.0", list.toString());
	}

	@Test
	public void testNumArrayListSize() {
		NumArrayList list = new NumArrayList();

		assertEquals("Method SIZE should return 0, when list is constructed by default constructor.",
				0, list.size());

		list.add(1.0);
		list.add(2.0);
		list.add(3.0);

		assertEquals("Method SIZE should return the size of the list, " + 
				"i.e. the number of elements, in the sequence.",
				3, list.size());
	}

	@Test
	public void testNumArrayListEquals() {
		NumArrayList listA = new NumArrayList();
		NumArrayList listB = new NumArrayList();
		NumArrayList listC = new NumArrayList();

		listA.add(1.0);
		listA.add(3.0);

		assertFalse("EQUALS method should return FALSE, when two lists are not the same.",
				listA.equals(listB));

		listB.add(1.0);
		listB.add(3.0);

		assertTrue("EQUALS method should return TRUE, when two lists are the same.",
				listA.equals(listB));

		listC.add(3.0);
		listC.add(1.0);

		assertFalse("EQUALS method should return FALSE, even if the same " + 
				"numbers are in different order in two lists.",
				listA.equals(listC));
	}

	// Tests for NumLinkedList's fundamental functions
	@Test
	public void testNumLinkedListConstructorAndToString() {
		NumLinkedList list = new NumLinkedList();		
		assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
				"It also can be the problem in method TOSTRING.", 
				"", list.toString());
	}

	@Test
	public void testNumLinkedListAddAndToString() {
		NumLinkedList list = new NumLinkedList();

		list.add(1.0);
		list.add(3.0);
		list.add(2.0);

		assertEquals("Add method should add element to the end of list each time. " +
				"It's also can be the problem in method TOSTRING.",
				"1.0 3.0 2.0", list.toString());
	}

	@Test
	public void testNumLinkedListSize() {
		NumLinkedList list = new NumLinkedList();

		assertEquals("Method SIZE should return 0, when list is constructed by default constructor.",
				0, list.size());

		list.add(1.0);
		list.add(2.0);
		list.add(3.0);

		assertEquals("Method SIZE should return the size of the list, " + 
				"i.e. the number of elements, in the sequence.",
				3, list.size());
	}

	@Test
	public void testNumLinkedListEquals() {
		NumLinkedList listA = new NumLinkedList();
		NumLinkedList listB = new NumLinkedList();
		NumLinkedList listC = new NumLinkedList();

		listA.add(1.0);
		listA.add(3.0);

		assertFalse("EQUALS method should return FALSE, when two lists are not the same.",
				listA.equals(listB));

		listB.add(1.0);
		listB.add(3.0);

		assertTrue("EQUALS method should return TRUE, when two lists are the same.",
				listA.equals(listB));

		listC.add(3.0);
		listC.add(1.0);

		assertFalse("EQUALS method should return FALSE, even if the same " + 
				"numbers are in different order in two lists.",
				listA.equals(listC));
	}

	/* Other JUnit tests.
	 * 
	 * Write your own JUnit tests below to check the correctness of your implementation.
	 * 
	 * When you turn in your draft (and final) we will run our own test suite on your code 
	 * and provide you with the feedback.
	 * 
	 * Your draft code should contain a complete set of methods as specified in the assignment.
	 * Any methods not yet implemented should be written as skeleton methods with an empty body. 
	 * 
	 * The JUnit tests below help to ensure that your methods compile with our test suite and have 
	 * correctly typed arguments. You can replace them with more meaningful tests to test their
	 * functionalities.
	 */

	// Skeleton tests for class NumArrayList
	@Test
	public void testNumArrayListConstructorWithCapacity() {
		NumArrayList list = new NumArrayList(7);
		assertNotNull("A constructor with an integer argument should initialize an " + 
				"empty list with that capacity.", list);
	}

	@Test
	public void testNumArrayListCapacity() {
		NumArrayList list = new NumArrayList();
		assertNotNull("Method CAPACITY should return the capacity of the array.",
				list.capacity());
	}

	@Test
	public void testNumArrayListInsert() {
		NumArrayList list = new NumArrayList();	
		list.insert(7, 7.0);
		assertTrue("Method INSERT put a new element before the i-th element" +
				" of the sequence (using 0 for the index of the first element).", true);
	}

	@Test
	public void testNumArrayListRemove() {
		NumArrayList list = new NumArrayList();
		list.remove(7);
		assertTrue("Method REMOVE remove the i-th element of the sequence.", true);
	}

	@Test
	public void testNumArrayListContains() {
		NumArrayList list = new NumArrayList();
		assertNotNull("Method CONTAINS return true if the list contains value," + 
				" false if it doesn’t.", list.contains(7.0));
	}

	@Test
	public void testNumArrayListLookup() {
		NumArrayList list = new NumArrayList();
		try {
			assertNotNull("Method LOOKUP return the i-th element of the sequence" + 
					" (raise an exception if the sequence has fewer than i elements).", 
					list.lookup(7));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testNumArrayListRemoveDuplicates() {
		NumArrayList list = new NumArrayList();
		list.removeDuplicates();
		assertTrue("Method REMOVEDUPLICATES remove all duplicates except the first " +
				"element in the list to preseve the list order.", true);
	}

	// Skeleton tests for class NumLinkedList

	@Test
	public void testNumLinkedListInsert() {
		NumLinkedList list = new NumLinkedList();
		list.insert(7, 7.0);
		assertTrue("Method INSERT put a new element before the i-th element" +
				" of the sequence (using 0 for the index of the first element).", true);
	}

	@Test
	public void testNumLinkedListRemove() {
		NumLinkedList list = new NumLinkedList();
		list.add(7.0);
		list.remove(0);
		assertTrue("Method REMOVE remove the i-th element of the sequence.", true);
	}

	@Test
	public void testNumLinkedListContains() {
		NumLinkedList list = new NumLinkedList();
		assertNotNull("Method CONTAINS return true if the list contains value," + 
				" false if it doesn’t.", list.contains(7.0));
	}

	@Test
	public void testNumLinkedListLookup() {
		NumLinkedList list = new NumLinkedList();
		list.add(7.0);
		try {
			assertNotNull("Method LOOKUP return the i-th element of the sequence" + 
					" (raise an exception if the sequence has fewer than i elements).", 
					list.lookup(0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testNumLinkedListRemoveDuplicates() {
		NumLinkedList list = new NumLinkedList();
		list.removeDuplicates();
		assertTrue("Method REMOVEDUPLICATES remove all duplicates except the first " +
				"element in the list to preseve the list order.", true);
	}

	// Skeleton tests for class NumSet

	@Test
	public void testNumSetConstructor() {
		NumSet set = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Constructor for NumSet should take a double array to define the set.", set);
	}


	@Test
	public void testNumSetSize() {
		NumSet set = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Method SIZE return the number of items of the set.", set.size());
	}

	@Test
	public void testNumSetContains() {
		NumSet set = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Method CONTAINS returns true if the set contains value, " +
				"false otherwise.", set.contains(7.0));
	}

	@Test
	public void testNumSetIntersect() {
		NumSet setA = new NumSet(new double[]{1.0, 3.0, 7.0});
		NumSet setB = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Method INTERSECT return a new NumSet that is " +
				"the intersection of two NumSets.", NumSet.intersect(setA,setB));
	}

	@Test
	public void testNumSetUnion() {
		NumSet setA = new NumSet(new double[]{1.0, 3.0, 7.0});
		NumSet setB = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Method UNION return a new NumSet that is " +
				"the union of two NumSets.", NumSet.union(setA,setB));
	}

	@Test
	public void testNumSetToString() {
		NumSet set = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Method TOSTRING convert the contents of the set to a sting.", 
				set.toString());
	}

	@Test
	public void testNumSetEquivalence() {
		NumSet setA = new NumSet(new double[]{1.0, 3.0, 7.0});
		NumSet setB = new NumSet(new double[]{1.0, 3.0, 7.0});
		assertNotNull("Method EQUIVALENCE return true two NumSets contains identical items.", 
				NumSet.equivalence(setA,setB));
	}
}


