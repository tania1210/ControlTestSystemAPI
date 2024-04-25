package university.com.lab1;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class OperationOTest {
	 
    @Test
    public void testErrorMessageDisplayed() {
        String input = "abc\n"; // Введення символу замість числа
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        OperationO.main(null);

        String expectedErrorMessage = "incorrect type of data. You must enter the number";
        assertTrue(outContent.toString().contains(expectedErrorMessage));
        }
    
	 @Test
	 public void testValueUnderSquareRootLessThanZero() {
	     String input = "5\n3\n"; // Коректне число з плаваючою комою
	     ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
	     System.setIn(in);
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     OperationO.main(null);
	     String expectedOutput = "x is NaN";
	     assertTrue(outContent.toString().contains(expectedOutput));
	 }
	 
	 @Test
	 public void testValueUnderSquareRootMoreThanZero() {
	     String input = "2\n9\n"; // Коректне число з плаваючою комою
	     ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
	     System.setIn(in);
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     OperationO.main(null);
	     String expectedOutput = "x = 17.549928774784245";
	     assertTrue(outContent.toString().contains(expectedOutput));
	 }


}
