import java.io.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Parser in assignment 3 in Computer programming II
 * (1TD722): Numeric calculator.
 *
 * @author Malin Kallen
 * @version 5
 */
@RunWith(JUnit4.class)
public class ParserTest {
  private InputStream stdin;
  private Map<String, Double> variables;
  
  @Before
  public void setUp() {
    stdin = System.in;
    variables = new TreeMap<String, Double>();
  }
  
  @Test
  public void testArithmetics() {
    verifyCalculation(1.5, "1 - (5 - 2*2)/(1+1) - (-2 + 1)");
    verifyCalculation(0.0, "sin(3.14159265)");
    verifyCalculation(Math.E, "exp(4*0.5 - 1)");
    verifyCalculation(10.0, "exp(log(10))");
    verifyCalculation(1.0, "sin(2)*sin(2) + cos(2)*cos(2)");
    verifyCalculation(-1.0, "1+-2");
    verifyCalculation(3.0, "1--2");
  }
  
  @Test
  public void testAssignment() {
    verifyCalculation(6.0, "1 + 2 + 3 = x");
    verifyCalculation(6.0, "x");
    verifyCalculation(9.0, "x/2 + x");
    verifyCalculation(1.90929743, "(1=x) + sin(2=y)");
    verifyCalculation(1.0, "x");
    verifyCalculation(2.0, "y");
  }
  
  @Test
  public void testExceptions() {
    verifySyntaxExceptionThrown("1++2");
    verifySyntaxExceptionThrown("1**2");
    verifyEvaluationExceptionThrown("1/0");
    verifyEvaluationExceptionThrown("1+2*k-4");
    verifySyntaxExceptionThrown("1+2=3+4**x - 1/0");
    verifySyntaxExceptionThrown("1+2*(3-1 a");
    verifySyntaxExceptionThrown("1+2+3+");
    verifySyntaxExceptionThrown("1+?");
    verifySyntaxExceptionThrown("3/#");
  }
  
  @After
  public void tearDown() {
    System.setIn(stdin);
  }
  
  /**
   * Replace System.in with a byte stream consisting of the content of the
   * statement given as argument and create a parser which reads this byte
   * stream and uses the member varaible "variables" as variable map.
   * 
   * @param statement Statement to parse
   * @return The parser described above
   */
  private Parser createParser(String statement) {
    System.setIn(new ByteArrayInputStream(statement.getBytes()));
    Stokenizer tokenizer = new Stokenizer();
    tokenizer.nextToken();
    return new Parser(tokenizer, variables);
  }
  
  /**
   * Verify that assignment in Parser returns the expected value when given the
   * specified statement.
   * 
   * @param expectedResult Expected return value from assignment
   * @param statement Statement for the parser to parse
   */
  private void verifyCalculation(double expectedResult, String statement) {
    Parser parser = createParser(statement);
    try {
      assertEquals(statement, expectedResult, parser.assignment(), 1e-8);
    } catch (Exception e) {
      System.setIn(stdin);	// We must reset System.in before terminating
      fail("Exception of type " + e.getClass() + " thrown for statement " + statement);
    }
  }
  
  /**
   * Verify that the parser throws an EvaluationException when the method
   * assignment is given the specified statement.
   * 
   * @param statement Statement which should cause an EvaluationException
   */
  private void verifyEvaluationExceptionThrown(String statement) {
    Parser parser = createParser(statement);
    boolean evaluationExceptionThrown = false;
    try {
      parser.assignment();
    } catch (EvaluationException e) {
      evaluationExceptionThrown = true;
    }
    assertTrue("Expected evaluation exception for" + statement, evaluationExceptionThrown);
  }
  
  /**
   * Verify that the parser throws a SyntaxException when the method assignment
   * is given the specified statement.
   * 
   * @param statement Statement which should cause an EvaluationException
   */
  private void verifySyntaxExceptionThrown(String statement) {
    Parser parser = createParser(statement);
    boolean syntaxExceptionThrown = false;
    try {
      parser.assignment();
    } catch (SyntaxException e) {
      syntaxExceptionThrown = true;
    }
    assertTrue("Expected syntax exception for" + statement, syntaxExceptionThrown);
  }

}