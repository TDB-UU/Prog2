import junit.framework.TestCase;
import org.junit.*;

/**
 * Test class for BinarySearchTree in assignment 4 in Computer programming II
 * (1TD722): Small exercises on lists and binary search trees.
 *
 * @author Malin Kallen
 * @version 1
 */
public class BinarySearchTreeTest extends TestCase {
  
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
    assertEquals(0, emptyTree.size());
    assertEquals(8, nonEmptyTree.size());
  }
  
  @Test
  public void testHeight() {
    assertEquals(0, emptyTree.height());
    assertEquals(4, nonEmptyTree.height());
  }
  
  @Test
  public void testContains() {
    assertFalse(emptyTree.contains("Keron"));
    assertFalse(emptyTree.contains("Stour Jierat"));
    assertFalse(nonEmptyTree.contains("Lapporten"));
    assertTrue(nonEmptyTree.contains("Keron"));
    assertTrue(nonEmptyTree.contains("Stour Jierta"));
    assertTrue(nonEmptyTree.contains("Akka"));
  }
  
  @Test
  public void testCopy() {
    // A copy of a tree should be equal to the tree itself...
    BinarySearchTree anotherEmptyTree = emptyTree.copy();
    assertTrue(anotherEmptyTree.equals(emptyTree));
    BinarySearchTree anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue(anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting into the copy should not change the original tree.
    anotherNonEmptyTree.insert("Vistas");
    assertFalse(anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting into the original tree should not change the copy.
    nonEmptyTree.insert("Vistas");
    assertTrue(anotherNonEmptyTree.equals(nonEmptyTree));
    nonEmptyTree.insert("Tarfala");
    assertFalse(anotherNonEmptyTree.equals(nonEmptyTree));
  }
  
  @Test
  public void testEquals() {
    // A tree is equal to itself
    assertTrue(emptyTree.equals(emptyTree));
    assertTrue(nonEmptyTree.equals(nonEmptyTree));
    // Two empty trees are equal
    BinarySearchTree anotherEmptyTree = new BinarySearchTree();
    assertTrue(anotherEmptyTree.equals(emptyTree));
    // A tree is equal to a copy of itself
    BinarySearchTree anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue(anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting in different order --> Not (necessarily) equal
    nonEmptyTree.insert("Nallo");
    nonEmptyTree.insert("Njulla");
    anotherNonEmptyTree.insert("Njulla");
    anotherNonEmptyTree.insert("Nallo");
    assertFalse(nonEmptyTree.equals(anotherNonEmptyTree));
  }
  
  @Test
  public void testIPL() {
    assertEquals(0, emptyTree.ipl());
    assertEquals(23, nonEmptyTree.ipl());
  }
  
}