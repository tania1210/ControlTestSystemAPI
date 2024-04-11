package university.com.lab1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class OperationTest {
	Operation o = new Operation();
    private final InputStream originalSystemIn = System.in;

//testing of inputInteger
    
    @Test(expected = NoSuchElementException.class)
    public void testInputIntegerNonIntegerInput() {
        String input = "2.5\n"; // некоректне введення числа b (дробове число)
        provideInputInteger(input);
        o.inputInteger();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInputIntegerNonNumericInput() {
        String input = "text\n"; // некоректне введення (рядок замість числа)
        provideInputInteger(input);
        o.inputInteger();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInputIntegerLessThanOne() {
        String input = "0\n"; // введення числа b менше 1
        provideInputInteger(input);
        o.inputInteger();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInputIntegerMoreThanThree() {
        String input = "4\n"; // введення числа b більше 3
        provideInputInteger(input);
        o.inputInteger();
    }

// Допоміжний метод для надання введення для тесту
    private void provideInputInteger(String data) {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
    
//testing of inputDouble  

    @Test(expected = NoSuchElementException.class)
    public void testInputDoubleInvalidType() {
        String input = "text\n";
        provideInputDouble(input);
        o.inputDouble();
    }

    private void provideInputDouble(String data) {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
    
// String
    
    @Test(expected = NoSuchElementException.class)
    public void testIsCompleteYes() {
        String input = "yes\n";
        provideInputString(input); 
        o.isComplete();
    }


    @Test(expected = NoSuchElementException.class)
    public void testIsCompleteNo() {
        String input = "no\n";
        provideInputString(input);
        o.isComplete();        
    }



// Метод, який симулює введення користувача
    private void provideInputString(String data) {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }

//tests for makeOperation

        @Test
        public void testMakeOperationCase1() {
            double result = o.makeOperation(1, 1.0);
            assertEquals(-5.0, result, 0.001);
        }

        @Test
        public void testMakeOperationCase2() {
            double result = o.makeOperation(1, -5.3);
            assertEquals(51.7, result, 0.001);
        }

        @Test
        public void testMakeOperationCase3() {
            double result = o.makeOperation(2, 0.5);
            assertEquals(1, result, 0.001);
        }

        @Test
        public void testMakeOperationCase4() {
        	assertThrows(IllegalArgumentException.class, () -> {
        		o.makeOperation(2, 2);
        	});
        }

        @Test
        public void testMakeOperationCase5() {
        	assertThrows(ArithmeticException.class, () -> {
        		o.makeOperation(3, 0);
        	});
        }
    	
	@Test
	public void testMakeOperationOneCase1() {
		assertEquals(-5.0, o.makeOperation(1, 1.0), 0.001);
	}
	
	@Test
	public void testMakeOperationOneCase2() {
		assertEquals(51.7, o.makeOperation(1, -5.3), 0.001);
	}

	
	@Test
	public void testMakeOperationTwoCase1() {
		assertEquals(1, o.makeOperation(2, 0.5), 0.001);
	}
	
	@Test
	public void testMakeOperationTwoCase2() {
		assertEquals(2.3664319, o.makeOperation(2, -1.8), 0.001);
	}
	
	@Test
	public void testMakeOperationTwoException() {       
        assertThrows(IllegalArgumentException.class, () -> {
            o.makeOperation(2, 2);
        });
	}

	
	@Test
	public void testMakeOperationThreeCase1() {
		assertEquals(3.0, o.makeOperation(3, 1.0), 0.001);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testMakeOperationThreeException() {
			o.makeOperation(3, 0);
	}

	
}
