package university.com.lab2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.Test;

public class MainOTestlab2 {
	 @Test
	    public void testFindMinLocalMax() { //коректність роботи алгоритму
	        int[] array = {1, 10, 3, 8, 2, 7, 6, 9, 4};
	        assertEquals(7, MainO.findMinLocalMax(array)); //перевірка чи отриманий елемент такий же як і очікуваний
	   }

	 @Test
	    public void testFindMinLocalMax_EmptyArray() {// передано пустий масив
	        int[] array = {};
	        assertThrows(NoSuchElementException.class, () -> MainO.findMinLocalMax(array));
	    }

	    @Test
	    public void testFindMinLocalMax_ArrayLessThanThreeElements() {//передано масив де менше 3 елементів
	        int[] array = {1, 2};
	        assertThrows(NoSuchElementException.class, () -> MainO.findMinLocalMax(array));
	    }

	    @Test
	    public void testFindMinLocalMax_AllElementsSame() {// передано масив де всі елементи однакові
	        int[] array = {5, 5, 5, 5};
	        assertThrows(NoSuchElementException.class, () -> MainO.findMinLocalMax(array));
	    }

	    @Test
	    public void testFindMinLocalMax_AllElementsAscendingOrDescending() {//передано масив де немає локальних максимумів
	        int[] array = {1, 2, 3, 4, 5};
	        assertThrows(NoSuchElementException.class, () -> MainO.findMinLocalMax(array));
	    }

	    @Test
	    public void testFindMinLocalMax_LargeArray() {//робота програми з великим масивом
	        int[] array = new int[1000];
	        for (int i = 0; i < array.length; i++) {
	            array[i] = i + 1;
	        }
	        assertThrows(NoSuchElementException.class, () -> MainO.findMinLocalMax(array));
	    }


	    
}
