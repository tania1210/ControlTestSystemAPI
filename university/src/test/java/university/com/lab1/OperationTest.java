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
//
//    @Rule
//    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
//
//p    private final ByteArrayInputStream inputStreamContent = new ByteArrayInputStream("1\n2.5\nyes\n".getBytes());
//    private final InputStream originalSystemIn = System.in;
//    private final PrintStream originalSystemOut = System.out;
////
//    @Before
//    public void setUpInput() {
//        System.setIn(inputStreamContent);
//        System.setOut(new PrintStream(new ByteArrayOutputStream()));
//    }
	
//
//    @After
//    public void restoreSystemInput() {
//        System.setIn(originalSystemIn);
//    }
    
    @Test
    public void testGetB() {
    	Operation o = new Operation();
    	o.setB(0);
    	assertEquals(0, o.getB());
    }

    @Test
    public void testGetC() {
    	Operation o = new Operation();
    	o.setC(0);
    	assertEquals(0, o.getC(), 0.001);
    }
//
//
////testing of inputInteger
//
    @Test
    public void testInputIntegerCorrectInput() {
        String input = "2\n"; // коректне введення числа b
        provideInputInteger(input);
        o.inputInteger();
        assertEquals(2, Operation.getB());
    }
    
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
//
//    // Допоміжний метод для надання введення для тесту
    private void provideInputInteger(String data) {
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
//    
//////testing of inputDouble
//    
    @Test
    public void testInputDouble() {
        provideInputDouble("1\n");
        o.inputDouble();
        assertEquals(1, o.getC(), 0.001);
    }

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
//    @Test
//    public void testIsCompleteYes() {
//        String input = "yes\n";
//        provideInputString(input); 
//        o.isComplete();
//        assertTrue("yes".equals(o.getRuntime()));
//    }
    
//    @Test
//    public void testIsCompleteYes() {
//        String input = "yes\n";
//        provideInput(input); 
//        o.isComplete();
//        assertEquals("yes", o.getRuntime());
//    }


//    @Test
//    public void testIsCompleteNo() {
//        String input = "no\n";
//        provideInputString(input);
//        o.isComplete();
//        assertEquals("no", o.getRuntime());
//        
//    }



//    // Метод, який симулює введення користувача
//    private void provideInputString(String data) {
//        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
//        System.setIn(inputStream);
//    }
//    	
//    @Test(expected = InputMismatchException.class)
//    public void testInputDoubleString() {
//        Operation instance = new Operation();
//        System.setIn(new ByteArrayInputStream("text".getBytes()));
//
//        instance.inputDouble();
//        
//    }
//    
////	@Test(expected = NoSuchElementException.class)
////	public void testInputDouble() {
////        String input = "text\n";
////        provideInput(input);
////        Operation.inputDouble();
////	}
//
//    @Test
//    public void testInputDoubleInteger() {
//        Operation instance = new Operation();
//        System.setIn(new ByteArrayInputStream("1".getBytes()));
//
//        instance.inputDouble();
//    }
//
////    @Test
////    public void testInputDoubleException() {
////    	System.out.println("testInputDoubleException");
////        String input = "invalid\n"; // Неправильне введення числа c
////        InputStream in = new ByteArrayInputStream(input.getBytes());
////        System.setIn(in);
////
////        assertThrows(InputMismatchException.class, Operation::inputDouble);
////    }
////	
//    
//    
////tests for scanner
//	@Test
//	public void testInputIntegerCase1() {
//		System.out.println("testInputIntegerCase1");
//        String input = "text\n";
//        provideInput(input);
//        assertThrows(InputMismatchException.class, () -> {
//            Operation.inputInteger();
//        });
//	}
//	
//	
//	
//	
//	@Test
//	public void testInputIntegerCase2() {
//        double input = 2.12;
//        assertThrows(InputMismatchException.class, () -> {
//            Operation.inputInteger();
//        });
//	}
//	
//	@Test
//	public void testInputIntegerCase3() {
//		int input = 0;
//		assertThrows(InputMismatchException.class, () -> {
//			Operation.inputInteger();
//		});
//	}
//	
//	
//    private void provideInput(String data) {
//        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
//        System.setIn(inputStream);
//    }
//	
//	
////tests for makeOperation
//

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
    

//	
//	@Test
//	public void testMakeOperationOneCase1() {
//		assertEquals(-5.0, Operation.makeOperation(1, 1.0), 0.001);
//	}
//	
//	@Test
//	public void testMakeOperationOneCase2() {
//		assertEquals(51.7, Operation.makeOperation(1, -5.3), 0.001);
//	}
//
//	
//	@Test
//	public void testMakeOperationTwoCase1() {
//		assertEquals(1, Operation.makeOperation(2, 0.5), 0.001);
//	}
//	
//	@Test
//	public void testMakeOperationTwoCase2() {
//		assertEquals(2.3664319, Operation.makeOperation(2, -1.8), 0.001);
//	}
//	
//	@Test
//	public void testMakeOperationTwoException() {       
//        assertThrows(IllegalArgumentException.class, () -> {
//            Operation.makeOperation(2, 2);
//        });
//	}
//
//	
//	@Test
//	public void testMakeOperationThreeCase1() {
//		assertEquals(3.0, Operation.makeOperation(3, 1.0), 0.001);
//	}
//	
////	@Test(expected = ArithmeticException.class)
////	public void testMakeOperationThreeException() {
////			Operation.makeOperation(3, 0);
////	}
//	
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
}
