package university.com.lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationO {

	public static void main(String[] args) {//1: початок
		
		Scanner s = new Scanner(System.in);//ініціалізація сканера
		
		try {//початок блоку try-catch
			System.out.println("enter value of a: ");
			double a = s.nextDouble();//введення значення а
			
			System.out.println("enter value of y: ");
			double y = s.nextDouble();//введення значення y
			
			double x = a * Math.sqrt(Math.pow(y, 2) - 2 * a);//обчислення значення x
			
			if(Double.isNaN(x)) {//перевірка чи x не є NaN
				System.out.println("x is NaN");
			}else {//якщо ні, виводимо його значення
				System.out.println("x = " + x);
			}
		}catch(InputMismatchException e) {//ловимо помилку, якщо буде введено символ замість числа
			System.out.println("incorrect type of data. You must enter the number");
		}
	
	}

}
