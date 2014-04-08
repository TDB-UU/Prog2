import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for List class in assignment 4 in Computer programming II
 * (1TD722): Small exercises on lists and binary search trees.
 *
 * @author Malin Kallen
 * @version 2
 */
@RunWith(JUnit4.class)
public class ListTest {
  
  List nonEmptyList;
  List emptyList;
  
  @Before
  public void setUp() {
    emptyList = new List();
    nonEmptyList = new List();
    nonEmptyList.putFirst(100);
    nonEmptyList.putFirst(52);
    nonEmptyList.putFirst(40);
    nonEmptyList.putFirst(37);
    nonEmptyList.putFirst(21);
  }
  
  @Test
  public void testLength() {
    // Length of an empty list is 0
    assertEquals(0, emptyList.length());
    // Length of a non-empty list is the number of elements
    assertEquals(5, nonEmptyList.length());    
  }
  
  @Test
  public void testContains() {
    // An empty list doesn't contain any elements...
    assertFalse(emptyList.contains(0));
    assertFalse(emptyList.contains(21));
    // First element shall be found
    assertTrue(nonEmptyList.contains(21));
    // Element in the middle of the list as well
    assertTrue(nonEmptyList.contains(52));
    // ... and the last element
    assertTrue(nonEmptyList.contains(100));
    // ... but not an element which is not there
    assertFalse(nonEmptyList.contains(0));
  }
  
  @Test
  public void testGetLast() {
    assertEquals(100, nonEmptyList.getLast());
  }
  
  @Test(expected=ListException.class)
  public void testGetLastFromEmptyList() {
    emptyList.getLast();
  }
  
  @Test
  public void testAtIndex() {
    assertEquals(21, nonEmptyList.atIndex(0));
    assertEquals(37, nonEmptyList.atIndex(1));
    assertEquals(40, nonEmptyList.atIndex(2));
    assertEquals(52, nonEmptyList.atIndex(3));
    assertEquals(100, nonEmptyList.atIndex(4));
  }
  
  @Test(expected=ListException.class)
  public void testAtIndex_emptyList() {
	  emptyList.atIndex(0);
  }
  
  @Test(expected=ListException.class)
  public void testAtIndex_negativeNumber() {
	  emptyList.atIndex(-1);
  }
  
  @Test(expected=ListException.class)
  public void testAtIndex_nonExistentNumber() {
	  emptyList.atIndex(5);
  }
  
  @Test
  public void testRemoveFirst() {
    assertEquals(21, nonEmptyList.removeFirst());
    assertEquals(37, nonEmptyList.removeFirst());
    assertEquals(40, nonEmptyList.removeFirst());
    assertEquals(52, nonEmptyList.removeFirst());
    assertEquals(100, nonEmptyList.removeFirst());
    assertEquals(0, nonEmptyList.length());
  }
  
  @Test(expected=ListException.class)
  public void testRemoveFirstFromEmptyList() {
    emptyList.removeFirst();
  }
  
  @Test
  public void testRemoveLast() {
    assertEquals(100, nonEmptyList.removeLast());
    assertEquals(52, nonEmptyList.removeLast());
    assertEquals(40, nonEmptyList.removeLast());
    assertEquals(37, nonEmptyList.removeLast());
    assertEquals(21, nonEmptyList.removeLast());
    assertEquals(0, nonEmptyList.length());
  }
  
  @Test(expected=ListException.class)
  public void testRemoveLastFromEmptyList() {
    emptyList.removeLast();
  }
  
  @Test
  public void testEquals() {
    // A list is equal to itself
    assertTrue(emptyList.equals(emptyList));
    assertTrue(nonEmptyList.equals(nonEmptyList));
    // ... and to an identical list
    List anotherEmptyList = new List();
    assertTrue(emptyList.equals(anotherEmptyList));
    // ... or a copy of itself
    List nonEmptyCopy = nonEmptyList.copy();
    assertTrue(nonEmptyList.equals(nonEmptyCopy));
    // ... unless the identical list or copy is modified
    nonEmptyCopy.putFirst(3);
    assertFalse(nonEmptyList.equals(nonEmptyCopy));    
    // ... even if the length becomes the same (but the values different)
    nonEmptyCopy.removeLast();
    assertFalse(nonEmptyList.equals(nonEmptyCopy));
    // An empty list is different from a non-empty list
    assertFalse(emptyList.equals(nonEmptyList));
  }
  
  @Test
  public void testIntersection() {
    List intersection;
    // The intersection of an empty list and another empty list is empty
    intersection = emptyList.intersection(emptyList);
    assertEquals(0, intersection.length());
    List anotherEmptyList = emptyList.copy();
    intersection = emptyList.intersection(anotherEmptyList);
    assertEquals(0, intersection.length());
    // Actually, the intersection of an empty list and any list is empty
    intersection = emptyList.intersection(nonEmptyList);
    assertEquals(0, intersection.length());
    // And so is the intersection of two lists with no elements equal
    List anotherNonEmptyList = new List();
    anotherNonEmptyList.putFirst(2);
    anotherNonEmptyList.putFirst(1);
    intersection = nonEmptyList.intersection(anotherNonEmptyList);
    assertEquals(0, intersection.length());
    // An intersection of a list by itself is a copy of the list
    intersection = nonEmptyList.intersection(nonEmptyList);
    assertTrue(intersection.equals(nonEmptyList));
    intersection = anotherNonEmptyList.intersection(anotherNonEmptyList);
    assertTrue(intersection.equals(anotherNonEmptyList));
    // And finally, the intersection of two lists which only partly overlap
    anotherNonEmptyList.insert(37);
    anotherNonEmptyList.insert(52);
    anotherNonEmptyList.insert(572);
    intersection = nonEmptyList.intersection(anotherNonEmptyList);
    assertEquals(2, intersection.length());
    assertEquals(37, intersection.atIndex(0));
    assertEquals(52, intersection.atIndex(1));
    // Intersection may not modify the list!
    testAtIndex();
    assertEquals(0, emptyList.length());
    assertEquals(0, anotherEmptyList.length());
    assertEquals(5, anotherNonEmptyList.length());
    assertEquals(1, anotherNonEmptyList.atIndex(0));
    assertEquals(2, anotherNonEmptyList.atIndex(1));
    assertEquals(37, anotherNonEmptyList.atIndex(2));
    assertEquals(52, anotherNonEmptyList.atIndex(3));
    assertEquals(572, anotherNonEmptyList.atIndex(4));
  }  
}