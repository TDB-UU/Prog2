import java.util.ArrayList;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for BST in an assignment in Computer programming II
 * (1TD722): Small exercises on lists and binary search trees.
 *
 * @author Malin Kallen, Tom Smedsaas
 * @version 2016-09-22
 */
@RunWith(JUnit4.class)
public class BSTTest {
  
  BST nonEmptyTree, nonEmptyTreeCopy;
  BST emptyTree, emptyTreeCopy;
  
  @Before
  public void setUp() {
    emptyTree = new BST();
    emptyTreeCopy = emptyTree.copy();
    nonEmptyTree = new BST();
    nonEmptyTree.add("Keron");
    nonEmptyTree.add("Akka");
    nonEmptyTree.add("Rusjka");
    nonEmptyTree.add("Kebnekaise");
    nonEmptyTree.add("Unna Avrrik");
    nonEmptyTree.add("Drakryggen");
    nonEmptyTree.add("Stour Jierta");
    nonEmptyTree.add("Unna Sievgok");
    nonEmptyTreeCopy = nonEmptyTree.copy();
  }
  
  @Test
  public void testSmallestFromNonEmptytree(){
     assertEquals("Smallest in non-empty tree  'Akka'",
      nonEmptyTree.smallest(), "Akka");
     assertTrue(nonEmptyTree.equals(nonEmptyTreeCopy));
  }
  
    
  @Test(expected=BST.BSTException.class)
  public void testSmallestFromEmptyTree(){
     emptyTree.smallest();
  }
  
  @Test
  public void testSize() {
    assertEquals("Size of empty tree", 0, emptyTree.size());
    assertEquals("Size of non-empty tree", 8, nonEmptyTree.size());
    assertTrue(nonEmptyTree.equals(nonEmptyTreeCopy));
  }
  
  @Test
  public void testHeight() {
    assertEquals("Height of empty tree", 0, emptyTree.height());
    assertEquals("Height of non-empty tree", 4, nonEmptyTree.height());
    assertTrue(nonEmptyTree.equals(nonEmptyTreeCopy));
  }
  
  @Test
  public void testCopy() {
    // A copy of a tree should be equal to the tree itself...
    BST anotherEmptyTree = emptyTree.copy();
    assertTrue("Empty tree equals copy of itself",
      anotherEmptyTree.equals(emptyTree));
    BST anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue("Non-empty tree equals copy of itself",
      anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting into the copy should not change the original tree.
    anotherNonEmptyTree.add("Vistas");
    assertFalse("Non-empty tree equals modified copy of itself",
      anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting into the original tree should not change the copy.
    nonEmptyTree.add("Vistas");
    assertTrue("Modified non-empty tree equals copy of itself",
      anotherNonEmptyTree.equals(nonEmptyTree));
    nonEmptyTree.add("Tarfala");
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
    BST anotherEmptyTree = new BST();
    assertTrue("Two empty trees are equal", anotherEmptyTree.equals(emptyTree));
    // A tree is equal to a copy of itself
    BST anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue("Non-empty tree equals copy of itself",
      anotherNonEmptyTree.equals(nonEmptyTree));
    // Inserting in different order --> Not (necessarily) equal
    nonEmptyTree.add("Nallo");
    nonEmptyTree.add("Njulla");
    anotherNonEmptyTree.add("Njulla");
    anotherNonEmptyTree.add("Nallo");
    assertFalse("Non-empty trees equal after addion in different order",
      nonEmptyTree.equals(anotherNonEmptyTree));
  }
 
    
  @Test
  public void testToArrayList() {
     ArrayList<String> alist = new ArrayList<String>();
     BST  atree = new BST();
     atree.add("e");
     atree.add("a");
     atree.add("b");
     atree.add("d");
     atree.add("h");
     atree.add("g");
     atree.add("f");
     atree.add("k"); 
     alist.add("a");
     alist.add("b");
     alist.add("d");
     alist.add("e");
     alist.add("f");
     alist.add("g");
     alist.add("h");   
     alist.add("k");
     BST atreeCopy = atree.copy();
     assertTrue("ArrayList", alist.toString().equals(atree.toArrayList().toString()));
     alist.add("x");
     assertFalse("ArrayList", alist.toString().equals(atree.toArrayList().toString()));
     atree.add("x");
     atreeCopy.add("x");
     assertTrue("ArrayList", alist.toString().equals(atree.toArrayList().toString()));
     atree.add("y");
     atreeCopy.add("y");
     assertFalse("ArrayList", alist.toString().equals(atree.toArrayList().toString()));
     assertTrue(atree.equals(atreeCopy));
  }
  
  @Test
    public void testSameContents() {
    // A tree is equal to itself
    assertTrue("Empty tree equals itself", emptyTree.sameContents(emptyTree));
    assertTrue("Non-empty tree equals itself",
      nonEmptyTree.sameContents(nonEmptyTree));
    // Two empty trees are equal
    BST anotherEmptyTree = new BST();
    assertTrue("Two empty trees are equal", anotherEmptyTree.sameContents(emptyTree));
    // A tree is equal to a copy of itself
    BST anotherNonEmptyTree = nonEmptyTree.copy();
    assertTrue("Non-empty tree equals copy of itself",
      anotherNonEmptyTree.sameContents(nonEmptyTree));
    // Inserting in different order --> Not (necessarily) equal
    nonEmptyTree.add("Nallo");
    nonEmptyTreeCopy.add("Nallo");
    nonEmptyTree.add("Njulla");
    nonEmptyTreeCopy.add("Njulla");
    nonEmptyTree.add("Kaskasapakte");
    nonEmptyTreeCopy.add("Kaskasapakte");
    anotherNonEmptyTree.add("Kaskasapakte");
    anotherNonEmptyTree.add("Nallo");
    anotherNonEmptyTree.add("Njulla");
    assertTrue("Non-empty trees equal after addion in different order",
      nonEmptyTree.sameContents(anotherNonEmptyTree));
    assertTrue(nonEmptyTree.equals(nonEmptyTreeCopy));
  }
  
  @Test
  public void testIPL() {
    assertEquals("Internal path length of empty tree", 0, emptyTree.ipl());
    assertEquals("Internal path length of non-empty tree", 23,
      nonEmptyTree.ipl());
    assertTrue(nonEmptyTree.equals(nonEmptyTreeCopy));
  }
  
}
