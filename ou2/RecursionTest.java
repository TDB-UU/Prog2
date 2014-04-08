
import java.io.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Test class for assignment 2 in Computer programming II (1TD722):
 * Small exercises on recursion.
 *
 * @author Malin Kallen
 * @version 3
 */
@RunWith(JUnit4.class)
public class RecursionTest {
  
  // Note: Exercise 10 and 12 must be checked man
  
  /////////////////// Exercise 1 ///////////////////
  @Test
  public void testPower() {
    // Exponent 0 --> 1
    assertEquals(1, Recursion.power(5, 0), 0);
    assertEquals(1, Recursion.power(-5, 0), 0);
    
    // Exponent 1 --> base
    assertEquals(5, Recursion.power(5,1), 0);
    assertEquals(-5, Recursion.power(-5,1), 0);
    
    // Some larger exponents
    assertEquals(25, Recursion.power(5,2), 0);
    assertEquals(25, Recursion.power(-5,2), 0);
    assertEquals(-125, Recursion.power(-5,3), 0);
  }
  
  /////////////////// Exercise 2 ///////////////////
  @Test
  public void testMultiplicera() {
    // Multiplication with 0 --> 0
    assertEquals(0, Recursion.multiply(0, 0), 0);
    assertEquals(0, Recursion.multiply(5, 0), 0);
    
    // Multiplication with 1 --> same number
    assertEquals(5, Recursion.multiply(5, 1), 0);
    assertEquals(5, Recursion.multiply(1, 5), 0);
    
    // Some higer numbers
    assertEquals(25, Recursion.multiply(5, 5), 0);
    assertEquals(100, Recursion.multiply(5, 20), 0);
  }
  
  /////////////////// Exercise 4 ///////////////////
  @Test
  public void testHarmonic() {
    // harmonic(1) = 1
    assertEquals(1, Recursion.harmonic(1), 0.000000001);
    
    // harmonic(2) = 3/2
    assertEquals(1.5, Recursion.harmonic(2), 0.000000001);
    
    // harmonic(10) = 2.928968254...
    assertEquals(2.928968254, Recursion.harmonic(10), 0.000000001);
  }
  
  /////////////////// Exercise 5 ///////////////////
  @Test
  public void testLargest() {
    // Index 0 --> First element
    int [] testArray = {1};    
    assertEquals(1, Recursion.largest(testArray, 0));  
    int [] longerTestArray = {5, 7, 0, 32, 45};
    assertEquals(5, Recursion.largest(longerTestArray, 0));
    int [] negativeTestArray = {-57, -74, -106, -32, -45};
    assertEquals(-32, Recursion.largest(negativeTestArray, 4));
    
    // Larger index --> Largest element among elements 0,...,i
    assertEquals(7, Recursion.largest(longerTestArray, 1));
    assertEquals(7, Recursion.largest(longerTestArray, 2));
    assertEquals(32, Recursion.largest(longerTestArray, 3));
    assertEquals(45, Recursion.largest(longerTestArray, 4));
  }
   
  /////////////////// Exercise 9 ///////////////////
  @Test
  public void testHanoi() {
    // 1 disc
    String expectedOutput = expectedHanoiRow('A', 'B');
    checkHanoi('A', 'B', 'C', 1, expectedOutput);
    
    // 2 discs
    expectedOutput = expectedHanoiRow('A', 'C')
      + expectedHanoiRow('A', 'B')
      + expectedHanoiRow('C', 'B');
    checkHanoi('A', 'B', 'C', 2, expectedOutput);
    
    // 2 discs, other names
    expectedOutput = expectedHanoiRow('M', 'K')
      + expectedHanoiRow('M', 'H')
      + expectedHanoiRow('K', 'H');
    checkHanoi('M', 'H', 'K', 2, expectedOutput);
    
    // 4 discs
    expectedOutput = expectedHanoiRow('A', 'C')
      + expectedHanoiRow('A', 'B')
      + expectedHanoiRow('C', 'B')
      + expectedHanoiRow('A', 'C')
      + expectedHanoiRow('B', 'A')
      + expectedHanoiRow('B', 'C')
      + expectedHanoiRow('A', 'C')
      + expectedHanoiRow('A', 'B')
      + expectedHanoiRow('C', 'B')
      + expectedHanoiRow('C', 'A')
      + expectedHanoiRow('B', 'A')
      + expectedHanoiRow('C', 'B')
      + expectedHanoiRow('A', 'C')
      + expectedHanoiRow('A', 'B')
      + expectedHanoiRow('C', 'B');
    checkHanoi('A', 'B', 'C', 4, expectedOutput);
  }
  
  /////////////////// Exercise 17 ///////////////////
  @Test
  public void testReverseNumbers() throws IOException {
    // One number -> The number itself
    checkReverseNumbers("1", "\\s?1\\s\\s?");

    // One number > 10 -> Still the number itself
    checkReverseNumbers("254685", "\\s?254685\\s\\s?");

    // Several numbers -> The numbers in reverse order
    checkReverseNumbers("232\n56\n94\n85\n123654\n2\n5\n985",
    "\\s?985\\s\\s?5\\s\\s?2\\s\\s?123654\\s\\s?85\\s\\s?94\\s\\s?56\\s\\s?232\\s\\s?");
    
    // Non-digit -> Empty string (up to 2 whitespaces)
    checkReverseNumbers("apa", "\\s?\\s?");
    
    // Non digit between numbers -> Numbers before non-digit token in revers order
    checkReverseNumbers("232 56 end 94 85 123654 2 5 985",
    "\\s?56\\s\\s?232\\s\\s?");
  }
  
  /////////////////// Help methods ///////////////////
  /**
   * Verify that Recursion.hanoi writes a string matching the provided regular
   * expression to System.out when called with the provided arguments (from, to,
   * help, n).
   * 
   * @param from First argument to hanoi
   * @param to Second argument to hanoi
   * @param help Third argument to hanoi
   * @param n Fourth argument to hanoi
   * @param expectedOutput Regular expression which the string written to System.out by hanoi should match
   */
  private void checkHanoi(char from, char to, char help, int n, String expectedOutput) {
    PrintStream stdout = System.out;
    
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    Recursion.hanoi(from, to, help, n);
    assertTrue(output.toString().matches(expectedOutput));
    
    System.setOut(stdout);
  }
  
  /**
   * Verify that a call to Recursion.reverseNumbers writes a string matching the
   * provided regular expression to System.out when the provided string is
   * written to System.in.
   * 
   * @param input Test data which is written to System.in
   * @param expectedOutput Regular expression which the string written to System.out by reverseNumbers should match
   */
  private void checkReverseNumbers(String input, String expectedOutput) {
    PrintStream stdout = System.out;
    InputStream stdin = System.in;
    
    OutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Recursion.reverseNumbers();
    assertTrue(output.toString().matches(expectedOutput));
    
    System.setIn(stdin);
    System.setOut(stdout);
  }
  
  /**
   * Generate a regular expression which matches the string which can be expected
   * to be written by hanoi when a disc is moved from one rod to another.
   * 
   * @param to Name of rod which the disc is moved from
   * @param from Name of rod which the disc is moved to
   * @return Regular expression which should match the string written by hanoi when a disc is moved from "from" to "to"
   */
  private String expectedHanoiRow(char from, char to) {
    return "\\s*" + from + "\\s*->\\s*" + to + "\\s*\n";
  }
  
}
