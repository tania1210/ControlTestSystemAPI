package university.com.lab1;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

public class MainTest {
	@Test
    public void testValidInput() {
        String input = "5\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        try {
            Main.main(new String[]{});
        } catch (NoSuchElementException e) {
            fail("Виникла помилка: " + e.getMessage());
        }
    }

    @Test
    public void testNonNumericInput() {
        String input = "abc\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertThrows(InputMismatchException.class, () -> {
            Main.main(new String[]{});
        });
    }

    @Test
    public void testZeroDenominator() {
        String input = "1\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertThrows(ArithmeticException.class, () -> {
            Main.main(new String[]{});
        });
    }
}
