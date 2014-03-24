import org.junit.*;
import junit.framework.TestCase;
import java.io.*;
import java.util.*;

public class CalculatorTest extends TestCase {
  private PrintStream stdout;
  private InputStream stdin;
  
  @Before
  public void setUp() {
    stdout = System.out;
    stdin = System.in;
  }
  
  @Test
  public void testAns() {
    verifyStatement("> 2.0\n> 2.0\n", "1+1\n ans\n", 2);
    verifyStatement("> 14.0\n> 14.0\n> 17.0\n> 20.0\n", "2+5+7\n ans\n ans+3\n 3+ans\n", 4);
  }

  @Test
  public void testException() {
    verifySyntaxError("2++3\n");
    verifySyntaxError("1**2\n");
    verifyEvaluationError("1/0\n");
    verifyEvaluationError("1+2*k-4\n");
    verifySyntaxError("1+2=3+4**x - 1/0\n");
    verifySyntaxError("1+2*(3-1 a\n");
    verifySyntaxError("1+2+3+\n");
  }
  
  @After
  public void tearDown() {
    System.setIn(stdin);
    System.setOut(stdout);
  }
  
  
  /**
   * Verify that statement in Calculator generates a one-line output containing
   * the string "Evaluation error" (possibly with lower-case e) and thereafter
   * continues parsing next line after having parsed the specified statement.
   * 
   * @param statement Statement which should cause an evaluation error
   */
  private void verifyEvaluationError(String statement) {
    verifyStatement("> .*[E|e]valuation error.+\n> 3.0\n", statement + "3\n", 2);
  }
  
  /**
   * Verify that statement in Calculator generates a two-line output, where the
   * first line contains the string "Syntax error" (possibly with lower-case s)
   * and thereafter continues parsing next line after having parsed the specified
   * statement.
   * 
   * @param statement Statement which should cause an evaluation error
   */
  private void verifySyntaxError(String statement) {
    verifyStatement("> .*[S|s]yntax error.+\n.+\n> 3.0\n", statement + "3\n", 2);
  }
  
  /**
   * Verify that statement in Calculator generates the expected output after
   * having parsed the specified statement.
   * 
   * @param expectedOutput What is expected to be written to System.out
   * @param statements Statements which will be handled by the calculator
   * @param numberOfStatements Number of statements in the statements string (= number of times statement should be called)
   */
  private void verifyStatement(String expectedOutput, String statements, int numberOfStatements) {
    System.setIn(new ByteArrayInputStream(statements.getBytes()));
    OutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    
    Calculator calc = new Calculator(new Stokenizer());
    try {
      for (int i=0; i<numberOfStatements; i++) {
        calc.statement();
      }
    } catch (Exception e) {
      System.setIn(stdin);
      fail("Exception of type " + e.getClass() + " thrown for statement " + statements);
    }

    System.err.print(output);
    System.err.print(expectedOutput);
    assertTrue(output.toString().matches(expectedOutput));
  }
}