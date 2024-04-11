package university.com.lab2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*Даний масив A розміру N. 
 * Знайти максимальний елемент з його елементів з непарними номерами: A1, A3, A5 . .*/

public class Main {

	public static boolean sizeIsCorrect = false;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		byte n = 0;
		try {
			System.out.println("input size of array: ");
			n = s.nextByte();
			sizeIsCorrect = true;
		}catch(InputMismatchException e) {
			System.out.println("uncorrect input data");
			sizeIsCorrect = false;
		}
		
		List<Number> a = new ArrayList();
		System.out.println("fill array: ");
		
		for(byte i = 0; i < n ; i++) {
			String input = s.next();
			
			try {
				a.add(Integer.parseInt(input));
			}catch(NumberFormatException e1) {
				try {
					a.add(Double.parseDouble(input));
				}catch(NumberFormatException e2) {
					System.out.println("type is wrong. Try integer or double");
					i--;
				}
			}
		}
		
		if(a.isEmpty()) {
			System.out.println("array is empty.");
		}else {
			for(Object i: a) {
				System.out.print(i + "  ");
			}
			
			byte maxId = -1; 
		    Number maxValue = null;
			for(byte i = 0; i < a.size() ; i++) {
			    if (i % 2 != 0) {
			        if (maxValue == null || a.get(i).doubleValue() > maxValue.doubleValue()) {
			            maxValue = a.get(i);
			            maxId = i;
			        }
			    }	
			}
			if(maxValue == null) {
				System.out.println("\nThere are any max element");
			}else {
				System.out.println("\nmaxValue is " + maxValue + ". It has " + maxId + " id");
			}
		}
	}


}
