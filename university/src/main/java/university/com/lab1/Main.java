package university.com.lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть значення x: ");
            double x = scanner.nextDouble();
            System.out.print("Введіть значення y: ");
            double y = scanner.nextDouble();
            
            double denominator = x * y - 2 * x + y;
            if (denominator != 0) {
                double a = (x * y + 2 * x - y) / denominator;
                System.out.println("Значення a: " + a);
            } else {
                System.out.println("Знаменник не може бути рівним нулю.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Помилка: Введені дані повинні бути числовими.");
        } finally {
            scanner.close();
        }
    }
}
