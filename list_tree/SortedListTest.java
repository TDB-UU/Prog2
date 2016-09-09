import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for List class in an assignment in Computer programming II
 * (1TD722): Small exercises on lists and binary search trees.
 *
 * @author Malin Kallen, Tom Smedsaas, Johan Ofverstedt
 * @version 7
 */
@RunWith(JUnit4.class)
public class SortedListTest {
  
  SortedList nonEmptyList, nonEmptyListCopy;
  SortedList emptyList, emptyListCopy;
  
  @Before
  public void setUp() {
    emptyList = new SortedList();
    emptyListCopy = emptyList.copy();
    nonEmptyList = new SortedList();
    nonEmptyList.add(100);
    nonEmptyList.add(52);
    nonEmptyList.add(40);
    nonEmptyList.add(37);
    nonEmptyList.add(21);
    nonEmptyListCopy = nonEmptyList.copy();
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
    // Verify that contains didn't modify the list
    assertTrue("Empty list modified by contains.",
            emptyList.equals(emptyListCopy));
    assertTrue("Non-empty list modified by contains.",
            nonEmptyList.equals(nonEmptyListCopy));
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
    // Verify that containsIter didn't modify the list
    assertTrue("Empty list modified by containsIter.",
            emptyList.equals(emptyListCopy));
    assertTrue("Non-empty list modified by containsIter.",
            nonEmptyList.equals(nonEmptyListCopy));
  }
    
  @Test
  public void testGetLast() {
    assertEquals("getLast", 100, nonEmptyList.getLast());
 // Verify that getLast didn't modify the list
    assertTrue("List modified by getLast.",
            nonEmptyList.equals(nonEmptyListCopy));
  }
  
  @Test(expected=SortedList.ListException.class)
  public void testGetLastFromEmptyList() {
    emptyList.getLast();
  }
 
  @Test
  public void testGetLastIter() {
    assertEquals("getLastIter", 100, nonEmptyList.getLastIter());
 // Verify that getLastIter didn't modify the list
    assertTrue("List modified by getLastIter.",
            nonEmptyList.equals(nonEmptyListCopy));
  }
  
  @Test(expected=SortedList.ListException.class)
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
    // Verify that atIndex didn't modify the list
    assertTrue("List modified by atIndex.",
            nonEmptyList.equals(nonEmptyListCopy));
  }
  
  @Test(expected=SortedList.ListException.class)
  public void testAtIndex_emptyList() {
   emptyList.atIndex(0);
  }
  
  @Test(expected=SortedList.ListException.class)
  public void testAtIndex_negativeNumber() {
   nonEmptyList.atIndex(-1);
  }
  
  @Test(expected=SortedList.ListException.class)
  public void testAtIndex_nonExistentNumber() {
   emptyList.atIndex(5);
  }
  
  @Test
  public void testIndexOf() {
     assertEquals("Value 21 in non-empty list", 0, nonEmptyList.indexOf(21));
     assertEquals("Value 37 in non-empty list", 1, nonEmptyList.indexOf(37));
     assertEquals("Value 40 in non-empty list", 2, nonEmptyList.indexOf(40));
     assertEquals("Value 52 in non-empty list", 3, nonEmptyList.indexOf(52));
     assertEquals("Value 100 in non-empty list", 4, nonEmptyList.indexOf(100)); 
     assertEquals("Value 99 in non-empty list", -1, nonEmptyList.indexOf(99));
     // Verify that indexOf didn't modify the list
     assertTrue("List modified by indexOf.",
             nonEmptyList.equals(nonEmptyListCopy));
  }
  
  @Test
  public void testClear() {
    nonEmptyList.clear();
    assertEquals("Cleared list of length > 0", 0, nonEmptyList.size());
  }
  
  @Test
  public void testRemoveLast() {
    assertEquals("removeLast, first call", 100, nonEmptyList.removeLast());
    assertEquals("removeLast, second call", 52, nonEmptyList.removeLast());
    assertEquals("removeLast, third call", 40, nonEmptyList.removeLast());
    assertEquals("removeLast, fourth call", 37, nonEmptyList.removeLast());
    assertEquals("removeLast, fifth call", 21, nonEmptyList.removeLast());
    assertEquals("Elements not removed by removeLast", 0, nonEmptyList.size());
  }
  
  @Test(expected=SortedList.ListException.class)
  public void testRemoveLastFromEmptyList() {
    emptyList.removeLast();
  }
  
  @Test
  public void testRemove() {
    int initialLength = nonEmptyList.size();
    nonEmptyList.remove(40);
    assertEquals("First remove didn't reduce list length.", initialLength - 1, nonEmptyList.size());
    assertFalse("List contains removed element 40", nonEmptyList.contains(40));
    nonEmptyList.remove(100);    
    assertEquals("Second remove didn't reduce list length.", initialLength - 2, nonEmptyList.size());
    assertFalse("List does not contain removed element 100", nonEmptyList.contains(100));
    nonEmptyList.remove(21);
    assertEquals("Third remove didn't reduce list length.", initialLength - 3, nonEmptyList.size());
    assertFalse("List does not contain removed element 21", nonEmptyList.contains(21));
  }
  
  @Test
  public void testMerge() {
    SortedList merged;
    // Merging an empty list and another empty -> Empty list
    merged = emptyList.merge(emptyList);
    assertEquals("Length of intersection of empty list with itself", 0,
      merged.size());
    merged = emptyList.merge(emptyListCopy);
    assertEquals("Length of intersection of two empty lists", 0,
      merged.size());
    // Merging empty and non-empty list --> A copy of the non-empty list
    merged = emptyList.merge(nonEmptyList);
    assertTrue(merged.equals(nonEmptyList));
    // Merging two non-empty lists --> List with all elements from the two lists 
    SortedList anotherNonEmptyList = new SortedList();
    anotherNonEmptyList.add(2);
    anotherNonEmptyList.add(1);
    merged = nonEmptyList.merge(anotherNonEmptyList);
    SortedList expectedMerged = nonEmptyList.copy();
    expectedMerged.add(2);
    expectedMerged.add(1);
    // Merging a list with itself --> List with every element existing twice
    merged = anotherNonEmptyList.merge(anotherNonEmptyList);
    expectedMerged.clear();
    expectedMerged.add(1); expectedMerged.add(1);
    expectedMerged.add(2); expectedMerged.add(2);
    assertTrue(expectedMerged.equals(merged));
    // Merging two lists that partly overlap --> All elements from both lists
    anotherNonEmptyList.add(37);
    anotherNonEmptyList.add(52);
    anotherNonEmptyList.add(572);
    SortedList anotherNonEmptyListCopy = anotherNonEmptyList.copy();
    merged = nonEmptyList.merge(anotherNonEmptyList);
    expectedMerged = nonEmptyList.copy();
    expectedMerged.add(572);
    expectedMerged.add(52);
    expectedMerged.add(37);
    expectedMerged.add(2);
    expectedMerged.add(1);
    assertTrue("Merging partly overlapping lists",
            merged.equals(expectedMerged));
    // Verify that merge didn't modify the lists
    assertTrue("List modified by merge.",
            nonEmptyList.equals(nonEmptyListCopy));
    assertTrue("List modified by merge.",
            anotherNonEmptyList.equals(anotherNonEmptyListCopy));
  }
 
  @Test
  public void testEquals() {
    // A list is equal to itself
    assertTrue("Empty does not equal itself", emptyList.equals(emptyList));
    assertTrue("Non-empty list does not equal itself",
      nonEmptyList.equals(nonEmptyList));
    // ... and to an identical list
    SortedList anotherEmptyList = new SortedList();
    assertTrue("Empty list does not equal other empty list",
      emptyList.equals(anotherEmptyList));
    // ... or a copy of itself
    SortedList nonEmptyCopy = nonEmptyList.copy();
    assertTrue("Non-empty list does not equal its copy",
      nonEmptyList.equals(nonEmptyCopy));
    // ... unless the identical list or copy is modified
    nonEmptyCopy.add(3);
    assertFalse("Non-empty list equals its copy after insertion",
      nonEmptyList.equals(nonEmptyCopy));    
    // ... even if the length becomes the same (but the values different)
    nonEmptyCopy.removeLast();
    assertFalse("Non-empty list equals its copy after insertion and deletion",
      nonEmptyList.equals(nonEmptyCopy));
    // An empty list is different from a non-empty list
    assertFalse("Empty list equals non-empty list",
      emptyList.equals(nonEmptyList));
    // Verify that equals didn't modify the lists
    assertTrue("List modified by equals", nonEmptyList.equals(nonEmptyListCopy));
  }
    
}
