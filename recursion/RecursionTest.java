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
 * @version 7
 * 
 * Edited by Johan Ofverstedt 2015-03-30
 */
@RunWith(JUnit4.class)
public class RecursionTest {
  
  /////////////////// Exercise 1 ///////////////////
  @Test
  public void testPower() {
    // Exponent 0 --> 1
    assertEquals("5^0", 1, Recursion.power(5, 0), 0);
    assertEquals("-5^0", 1, Recursion.power(-5, 0), 0);
    
    // Exponent 1 --> base
    assertEquals("5^1", 5, Recursion.power(5,1), 0);
    assertEquals("-5^1", -5, Recursion.power(-5,1), 0);
    
    // Some larger exponents
    assertEquals("5^2", 25, Recursion.power(5,2), 0);
    assertEquals("-5^2", 25, Recursion.power(-5,2), 0);
    assertEquals("-5^3", -125, Recursion.power(-5,3), 0);
  }
  
  /////////////////// Exercise 2 ///////////////////
  @Test
  public void testMultiplicera() {
    // Multiplication with 0 --> 0
    assertEquals("0 * 0", 0, Recursion.multiply(0, 0), 0);
    assertEquals("5 * 0", 0, Recursion.multiply(5, 0), 0);
    
    // Multiplication with 1 --> same number
    assertEquals("5 * 1", 5, Recursion.multiply(5, 1), 0);
    assertEquals("1 * 5", 5, Recursion.multiply(1, 5), 0);
    
    // Some higer numbers
    assertEquals("5 * 5", 25, Recursion.multiply(5, 5), 0);
    assertEquals("5 * 20", 100, Recursion.multiply(5, 20), 0);
  }
  
  /////////////////// Exercise 4 ///////////////////
  @Test
  public void testHarmonic() {
    // harmonic(1) = 1
    assertEquals("Harmonic 1", 1, Recursion.harmonic(1), 0.000000001);
    
    // harmonic(2) = 3/2
    assertEquals("Harmonic 2", 1.5, Recursion.harmonic(2), 0.000000001);
    
    // harmonic(10) = 2.928968254...
    assertEquals("Harmonic 10", 2.928968254, Recursion.harmonic(10),
            0.000000001);
  }
  
  /////////////////// Exercise 5 ///////////////////
  @Test
  public void testLargest() {
    // Index 0 --> First element
    int [] testArray = {1};
    assertEquals("Largest in array of length 1", 1,
            Recursion.largest(testArray, 0));
    int [] longerTestArray = {5, 7, 0, 32, 45};
    int [] longerTestArrayCopy = longerTestArray.clone();
    assertEquals("Largest of the first element in longer array", 5,
            Recursion.largest(longerTestArray, 0));
    int [] negativeTestArray = {-57, -74, -106, -32, -45};
    int [] negativeTestArrayCopy = negativeTestArray.clone();
    assertEquals("Largest in negative array", -32,
            Recursion.largest(negativeTestArray, 4));
    
    // Larger index --> Largest element among elements 0,...,i
    assertEquals("Largest 1", 7, Recursion.largest(longerTestArray, 1));
    assertEquals("Largest 2", 7, Recursion.largest(longerTestArray, 2));
    assertEquals("Largest 3", 32, Recursion.largest(longerTestArray, 3));
    assertEquals("Largest 4", 45, Recursion.largest(longerTestArray, 4));
    
    // Verify that the arrays were not changed by largest
    assertArrayEquals("Largest changed array with positive numbers",
    		longerTestArray, longerTestArrayCopy);
    assertArrayEquals("Largest changed array with negative numbers",
    		negativeTestArray, negativeTestArrayCopy);
  }
  
  
  /////////////////// Exercise 12 ///////////////////
  @Test
  public void testReverseNumbers() throws IOException {
    // One number -> The number itself
    checkReverseNumbers("1", "\\s?1\\s{0,2}", "1 number < 10");

    // One number > 10 -> Still the number itself
    checkReverseNumbers("254685", "\\s?254685\\s{0,2}", "1 number > 10");

    // Several numbers -> The numbers in reverse order
    checkReverseNumbers("232 56 94 85 123654 2 5 985",
    		"\\s?985\\s{1,2}5\\s{1,2}2\\s{1,2}123654\\s{1,2}85\\s{1,2}94\\s{1,2}56\\s{1,2}232\\s{0,2}",
    		"Several numbers");
    
    // Non-digit -> Empty string (up to 2 whitespaces)
    checkReverseNumbers("apa", "\\s{0,2}", "No numbers");
    
    // Non digit between numbers -> Numbers before non-digit token in reverse order
    checkReverseNumbers("232\n56\nend\n94\n85\n123654\n2\n5\n985",
    "\\s?56\\s{1,2}232\\s{0,2}", "Numbers after end");
  }
  
  /////////////////// Help methods ///////////////////
  /**
   * Verify that a call to Recursion.reverseNumbers writes a string matching the
   * provided regular expression to System.out when the provided string is
   * written to System.in.
   * 
   * @param input Test data which is written to System.in
   * @param expectedOutput Regular expression which the string written to System.out by reverseNumbers should match
   * @param message Identification message for AssertionError
   */
  private void checkReverseNumbers(String input, String expectedOutput, String message) {
    String output = Recursion.reverseNumbers(new java.util.Scanner(input));
    assertTrue(message, output.matches(expectedOutput));
  }
}
