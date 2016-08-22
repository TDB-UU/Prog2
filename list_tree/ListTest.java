import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for List class in an assignment in Computer programming II
 * (1TD722): Small exercises on lists and binary search trees.
 *
 * @author Malin Kallen
 * @version 5 (2015-04-22 test of more methods added by ts) 
 *            (2015-09-13 tests for 'clear' and 'remove' added by jo)
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
    assertEquals("Length of empty list", 0, emptyList.length());
    // Length of a non-empty list is the number of elements
    assertEquals("Length of non-empty list", 5, nonEmptyList.length());    
  }
  
  @Test
  public void testContains() {
    // An empty list doesn't contain any elements...
    assertFalse("Empty list contains 0", emptyList.contains(0));
    assertFalse("Empty list contains 21", emptyList.contains(21));
    // First element shall be found
    assertTrue("Non-empty list contains first element",
      nonEmptyList.contains(21));
    // Element in the middle of the list as well
    assertTrue("Non-empty list contains element in the middle",
      nonEmptyList.contains(52));
    // ... and the last element
    assertTrue("Non-empty list contains last-element",
      nonEmptyList.contains(100));
    // ... but not an element which is not there
    assertFalse("Non-empty list contains non-existing element",
      nonEmptyList.contains(0));
  }
  
    @Test
  public void testContainsIter() {
    // An empty list doesn't contain any elements...
    assertFalse("Empty list contains 0", emptyList.containsIter(0));
    assertFalse("Empty list contains 21", emptyList.containsIter(21));
    // First element shall be found
    assertTrue("Non-empty list contains first element",
      nonEmptyList.containsIter(21));
    // Element in the middle of the list as well
    assertTrue("Non-empty list contains element in the middle",
      nonEmptyList.containsIter(52));
    // ... and the last element
    assertTrue("Non-empty list contains last-element",
      nonEmptyList.containsIter(100));
    // ... but not an element which is not there
    assertFalse("Non-empty list contains non-existing element",
      nonEmptyList.containsIter(0));
  }
    
//  @Test
//  public void testGetLast() {
//    assertEquals("getLast", 100, nonEmptyList.getLast());
//  }
  
//  @Test(expected=List.ListException.class)
//  public void testGetLastFromEmptyList() {
//    emptyList.getLast();
//  }
 
  @Test
  public void testGetLastIter() {
    assertEquals("getLastIter", 100, nonEmptyList.getLastIter());
  }
  
  @Test(expected=List.ListException.class)
  public void testGetLastIterFromEmptyList() {
    emptyList.getLastIter();
  }
  
  @Test
  public void testAtIndex() {
    assertEquals("Element 0 in non-empty list", 21, nonEmptyList.atIndex(0));
    assertEquals("Element 1 in non-empty list", 37, nonEmptyList.atIndex(1));
    assertEquals("Element 2 in non-empty list", 40, nonEmptyList.atIndex(2));
    assertEquals("Element 3 in non-empty list", 52, nonEmptyList.atIndex(3));
    assertEquals("Element 4 in non-empty list", 100, nonEmptyList.atIndex(4));
  }
  
  @Test
  public void testIndexOf() {
     assertEquals("Element 21 in non-empty list", 0, nonEmptyList.indexOf(21));
     assertEquals("Element 37 in non-empty list", 1, nonEmptyList.indexOf(37));
     assertEquals("Element 40 in non-empty list", 2, nonEmptyList.indexOf(40));
     assertEquals("Element 52 in non-empty list", 3, nonEmptyList.indexOf(52));
     assertEquals("Element 100 in non-empty list", 4, nonEmptyList.indexOf(100)); 
     assertEquals("Element 99 in non-empty list", -1, nonEmptyList.indexOf(99));
  }
  
  @Test(expected=List.ListException.class)
  public void testAtIndex_emptyList() {
   emptyList.atIndex(0);
  }
  
  @Test(expected=List.ListException.class)
  public void testAtIndex_negativeNumber() {
   nonEmptyList.atIndex(-1);
  }
  
  @Test(expected=List.ListException.class)
  public void testAtIndex_nonExistentNumber() {
   emptyList.atIndex(5);
  }
  
  @Test
  public void testRemoveFirst() {
    assertEquals("removeFirst, first call", 21, nonEmptyList.removeFirst());
    assertEquals("removeFirst, second call", 37, nonEmptyList.removeFirst());
    assertEquals("removeFirst, third call", 40, nonEmptyList.removeFirst());
    assertEquals("removeFirst, fourth call", 52, nonEmptyList.removeFirst());
    assertEquals("removeFirst, fifth call", 100, nonEmptyList.removeFirst());
    assertEquals("Length after 5 calls to removeFirst", 0,
      nonEmptyList.length());
  }
  
  @Test(expected=List.ListException.class)
  public void testRemoveFirstFromEmptyList() {
    emptyList.removeFirst();
  }
  
  @Test
  public void testRemoveLast() {
    assertEquals("removeLast, first call", 100, nonEmptyList.removeLast());
    assertEquals("removeLast, second call", 52, nonEmptyList.removeLast());
    assertEquals("removeLast, third call", 40, nonEmptyList.removeLast());
    assertEquals("removeLast, fourth call", 37, nonEmptyList.removeLast());
    assertEquals("removeLast, fifth call", 21, nonEmptyList.removeLast());
    assertEquals("Length after 5 calls to removeLast", 0, nonEmptyList.length());
  }
  
  
    @Test
    public void testInsertIter() {
      List anotherList = new List();
      anotherList.insertIter(5);
      anotherList.insertIter(8);
      anotherList.insertIter(2);
      anotherList.insertIter(6);
      anotherList.insertIter(7);
      assertEquals("first after insertIter", 2, anotherList.removeFirst());
      assertEquals("second after insertIter", 5, anotherList.removeFirst());
      assertEquals("third after insertIter", 6, anotherList.removeFirst());
      assertEquals("fourth after insertIter", 7, anotherList.removeFirst());
      assertEquals("fifth after insertIter", 8, anotherList.removeFirst());
    }
    
    
  @Test(expected=List.ListException.class)
  public void testRemoveLastFromEmptyList() {
    emptyList.removeLast();
  }
  
  @Test
  public void testClear() {
    nonEmptyList.clear();
    assertTrue("Cleared list is empty.",
      nonEmptyList.isEmpty());
  }

  @Test
  public void testRemove() {
    int initialLength = nonEmptyList.length();

    nonEmptyList.remove(40);
    
    assertEquals("Length reduced by 1",
                 initialLength - 1,
                 nonEmptyList.length());
    
    assertFalse("List does not contain removed element",
                nonEmptyList.contains(40));

    nonEmptyList.remove(100);
    
    assertEquals("Length reduced by 2",
                 initialLength - 2,
                 nonEmptyList.length());
    
    assertFalse("List does not contain removed element",
                nonEmptyList.contains(100));

    nonEmptyList.remove(21);
    
    assertEquals("Length reduced by 3",
                 initialLength - 3,
                 nonEmptyList.length());
    
    assertFalse("List does not contain removed element",
                nonEmptyList.contains(21));

  }  
  
  @Test
  public void testEquals() {
    // A list is equal to itself
    assertTrue("Empty list equals itself", emptyList.equals(emptyList));
    assertTrue("Non-empty list equals itself",
      nonEmptyList.equals(nonEmptyList));
    // ... and to an identical list
    List anotherEmptyList = new List();
    assertTrue("Empty list equals other empty list",
      emptyList.equals(anotherEmptyList));
    // ... or a copy of itself
    List nonEmptyCopy = nonEmptyList.copy();
    assertTrue("Non-empty list equals its copy",
      nonEmptyList.equals(nonEmptyCopy));
    // ... unless the identical list or copy is modified
    nonEmptyCopy.putFirst(3);
    assertFalse("Non-empty list equals its copy after insertion",
      nonEmptyList.equals(nonEmptyCopy));    
    // ... even if the length becomes the same (but the values different)
   // nonEmptyCopy.removeLast();
    nonEmptyCopy.putFirst(-100);
    assertFalse("Non-empty list equals its copy after insertion and deletion",
      nonEmptyList.equals(nonEmptyCopy));
    // An empty list is different from a non-empty list
    assertFalse("Empty list equals non-empty list",
      emptyList.equals(nonEmptyList));
  }
  
  @Test
  public void testIntersection() {
    List intersection;
    // The intersection of an empty list and another empty list is empty
    intersection = emptyList.intersection(emptyList);
    assertEquals("Length of intersection of empty list with itself", 0,
      intersection.length());
    List anotherEmptyList = emptyList.copy();
    intersection = emptyList.intersection(anotherEmptyList);
    assertEquals("Length of intersection of two empty lists", 0,
      intersection.length());
    // Actually, the intersection of an empty list and any list is empty
    intersection = emptyList.intersection(nonEmptyList);
    assertEquals("Length of intersection of empty and non-empty list", 0,
      intersection.length());
    // And so is the intersection of two lists with no elements equal
    List anotherNonEmptyList = new List();
    anotherNonEmptyList.putFirst(2);
    anotherNonEmptyList.putFirst(1);
    intersection = nonEmptyList.intersection(anotherNonEmptyList);
    assertEquals("Length of intersection of disjuctive non-empty lists", 0,
      intersection.length());
    // An intersection of a list by itself is a copy of the list
    intersection = nonEmptyList.intersection(nonEmptyList);
    assertTrue("Intersection of list by itself",
      intersection.equals(nonEmptyList));
    intersection = anotherNonEmptyList.intersection(anotherNonEmptyList);
    assertTrue("Intersection of another list by itself",
      intersection.equals(anotherNonEmptyList));
    // And finally, the intersection of two lists which only partly overlap
    anotherNonEmptyList.insert(37);
    anotherNonEmptyList.insert(52);
    anotherNonEmptyList.insert(572);
    intersection = nonEmptyList.intersection(anotherNonEmptyList);
    assertEquals("Length of intersection of partly overlapping lists", 2,
      intersection.length());
    assertEquals("First element of partly overlapping lists", 37,
      intersection.atIndex(0));
    assertEquals("Second element of partly overlapping lists", 52,
      intersection.atIndex(1));
    // Intersection may not modify the list!
    assertEquals("Empty list after intersecion calls", 0, emptyList.length());
    assertEquals("The other empty list after intersection calls", 0,
      anotherEmptyList.length());
    assertEquals("Length of non-empty list after calls to instersection", 5,
      anotherNonEmptyList.length());
    assertEquals("First element of non-empty list after calls to instersection",
      1, anotherNonEmptyList.atIndex(0));
    assertEquals("Second element of non-empty list after calls to instersection",
      2, anotherNonEmptyList.atIndex(1));
    assertEquals("Third element of non-empty list after calls to instersection",
      37, anotherNonEmptyList.atIndex(2));
    assertEquals("Fourth element of non-empty list after calls to instersection",
      52, anotherNonEmptyList.atIndex(3));
    assertEquals("Fifth element of non-empty list after calls to instersection",
      572, anotherNonEmptyList.atIndex(4));
    testAtIndex();
  }  
}
