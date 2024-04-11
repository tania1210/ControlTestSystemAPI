package university.com.lab2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.List;
import java.io.InputStream;

import org.junit.Test;


public class MainTestlab2 {
	Main main;
	@Test
	public void testInvalidInputSize() {
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // Буфер для перехоплення виведення
	    System.setOut(new PrintStream(outContent)); // Перенаправляємо виведення з консолі у наш буфер
	    System.setIn(new ByteArrayInputStream("а\n".getBytes())); // Встановлюємо вхідні дані
	    Main.main(null); // Викликаємо головний метод програми
	    // Перевірка виведення на консоль
	    assertTrue(outContent.toString().contains("uncorrect input data")); // Перевіряємо, що немає повідомлення про некоректні дані
 }
	
	@Test
	public void testCorrectInputSize() {
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // Буфер для перехоплення виведення
	    System.setOut(new PrintStream(outContent)); // Перенаправляємо виведення з консолі у наш буфер
	    ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
	    System.setIn(in);	    
	    Main.main(null);
	    assertTrue(main.sizeIsCorrect);
	}	
	
	@Test
    public void testProcessInputValidInteger() {
        String input = "5\n1\n2\n3\n4\n5\n"; // Коректне ціле число
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(null);
        assertEquals("input size of array: \nfill array: \n1  2  3  4  5  \nmaxValue is 4. It has 3 id\n", outContent.toString());
    }

	 @Test
	    public void testProcessInputValidDouble() {
	        String input = "1\n3.14\n"; // Коректне число з плаваючою комою
	        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));
	        Main.main(null);
	        assertEquals("input size of array: \nfill array: \n3.14  \nThere are any max element\n", outContent.toString());
	    }
 
	 @Test
	 public void testProcessInputInvalid() {
	     String input = "2\n7\na\n2.2\n"; // Некоректне введення
	     ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
	     System.setIn(in);
	     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     Main.main(null);

	     String expectedOutput = "input size of array: \nfill array: \ntype is wrong. Try integer or double\n7  2.2  \nmaxValue is 2.2. It has 1 id\n";
	     // Перевірка на точність порівняння виведеного результату з очікуваним
	     assertEquals(expectedOutput, outContent.toString());
	 }

}
	 
	 
	 
