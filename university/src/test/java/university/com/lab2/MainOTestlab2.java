package university.com.lab2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class MainOTestlab2 {
	 @Test
	    public void testFindMinLocalMax() { //коректність роботи алгоритму
	        int[] array = {1, 10, 3, 8, 2, 7, 6, 9, 4};
	        assertEquals(7, MainO.findMinLocalMax(array)); //перевірка чи отриманий елемент такий же як і очікуваний
	   }

	    @Test
	    public void testEmptyArray() { //якщо буде подано пустий масив
	        int[] array = {};
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MainO.findMinLocalMax(array));
	        assertEquals("Масив повинен містити принаймні 3 елементи", exception.getMessage());
	    }

	    @Test
	    public void testArrayWithLessThan3Elements() { //якщо масив містить менше 3 елементів
	        int[] array = {1, 2};
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MainO.findMinLocalMax(array));
	        assertEquals("Масив повинен містити принаймні 3 елементи", exception.getMessage());
	    }

	    @Test
	    public void testArrayWithoutLocalMax() {//якщо масив без локального максимуму
	        int[] array = {1, 2, 3, 4, 5};
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MainO.findMinLocalMax(array));
	        assertEquals("В масиві немає локальних максимумів", exception.getMessage());
	    }
	    
}
