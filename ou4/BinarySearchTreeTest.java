
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for BinarySearchTree in assignment 4 in Computer programming II
 * (1TD722): Small exercises on lists and binary search trees.
 *
 * @author Malin Kallen
 * @version 3
 */
@RunWith(JUnit4.class)
public class BinarySearchTreeTest {
  
  BinarySearchTree nonEmptyTree;
  BinarySearchTree emptyTree;
  
  @Before
  public void setUp() {
    emptyTree = new BinarySearchTree();
    nonEmptyTree = new BinarySearchTree();
    nonEmptyTree.insert("Keron");
    nonEmptyTree.insert("Akka");
    nonEmptyTree.insert("Rusjka");
    nonEmptyTree.insert("Kebnekaise");
    nonEmptyTree.insert("Unna Avrrik");
    nonEmptyTree.insert("Drakryggen");
    nonEmptyTree.insert("Stour Jierta");
    nonEmptyTree.insert("Unna Sievgok");
  }
  
  @Test
  public void testSize() {
    assertEquals("Size of empty tree", 0, emptyTree.size());
    assertEquals("Size of non-empty tree", 8, nonEmptyTree.size());
  }
  
  @Test
  public void testHeight() {
    assertEquals("Height of empty tree", 0, emptyTree.height());
    assertEquals("Height of non-empty tree", 4, nonEmptyTree.height());
  }
  
  @Test
  public void testContains() {
    assertFalse("Empty tree contains", emptyTree.contains("Keron"));
    assertFalse("Empty tree contains", emptyTree.contains("Stour Jierta"));
    assertFalse("Non-empty tree contains 'Lapporten'",
    		nonEmptyTree.contains("Lapporten"));
    assertTrue("Non-empty tree contains 'Keron'",
    		nonEmptyTree.contains("Keron"));
    assertTrue("Non-empty tree contains 'Stour Jierta'",
    		nonEmptyTree.contains("Stour Jierta"));
    assertTrue("Non-empty tree contains 'Akka'",
    		nonEmptyTree.contains("Akka"));
  }
  
  @Test
  public void testCopy() {
    // A copy of a tree should be equal to the tree itself...
    BinarySearchTree anotherEmptyTree = emptyTree.copy();
    assertTrue("Empty tree equals copy of itself",
    		anotherEmptyTree.equals(emptyTree));
    BinarySearchTree anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue("Non-empty tree equals copy of itself",
    		anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting into the copy should not change the original tree.
    anotherNonEmptyTree.insert("Vistas");
    assertFalse("Non-empty tree equals modified copy of itself",
    		anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting into the original tree should not change the copy.
    nonEmptyTree.insert("Vistas");
    assertTrue("Modified non-empty tree equals copy of itself",
    		anotherNonEmptyTree.equals(nonEmptyTree));
    nonEmptyTree.insert("Tarfala");
    assertFalse("Twice modified non-empty tree equals copy of itself",
    		anotherNonEmptyTree.equals(nonEmptyTree));
  }
  
  @Test
  public void testEquals() {
    // A tree is equal to itself
    assertTrue("Empty tree equals itself", emptyTree.equals(emptyTree));
    assertTrue("Non-empty tree equals itself",
    		nonEmptyTree.equals(nonEmptyTree));
    // Two empty trees are equal
    BinarySearchTree anotherEmptyTree = new BinarySearchTree();
    assertTrue("Two empty trees are equal", anotherEmptyTree.equals(emptyTree));
    // A tree is equal to a copy of itself
    BinarySearchTree anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue("Non-empty tree equals copy of itself",
    		anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting in different order --> Not (necessarily) equal
    nonEmptyTree.insert("Nallo");
    nonEmptyTree.insert("Njulla");
    anotherNonEmptyTree.insert("Njulla");
    anotherNonEmptyTree.insert("Nallo");
    assertFalse("Non-empty trees equal after insertion in different order",
    		nonEmptyTree.equals(anotherNonEmptyTree));
  }
  
  @Test
  public void testIPL() {
    assertEquals("Internal path length of empty tree", 0, emptyTree.ipl());
    assertEquals("Internal path length of non-empty tree", 23,
    		nonEmptyTree.ipl());
  }
  
}