package university.com.lab1;

import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Operation {
	static Scanner s = new Scanner(System.in);
	static int b; 
	static double c;
	static String runtime;
		
	public String getRuntime() {
		return runtime;
	}
	
    public static void main(String[] args) {// 1: початок
    	Operation o = new Operation();
        do {
        	o.inputInteger();//2: виклик методу для введення b
            o.inputDouble();//7: виклик методу для введення c
        	try {
                System.out.println("result: " + o.makeOperation(b, c) + "\n"); //11: виконання обчислення
    		}catch (IllegalArgumentException e) {//22: обробка винятків
    	        System.out.println("invalid arguments: " + e); 
    	    }catch (ArithmeticException e) {// 23: обробка винятків
    	        System.out.println("division by zero: " + e); 
    	    }
        	o.isComplete();//24: виклик методу для вибору дії
        } while (!runtime.equals("yes"));//27: вихід з програми

        s.close(); // 28: закриття сканера
    	
    }
	
	public void inputInteger() {//2: метод для введення b
		 boolean isValidInput = false;
		 
     	System.out.println("choose value of \"b\": (1, 2, 3)");

	        while (!isValidInput) {//3: початок циклу
	        	s = new Scanner(System.in);
	            try {
	        		b = s.nextInt();//4: зміна значення b
	        		if(b < 1 || b > 3) {//5: перевірка умов
	        			System.out.println("wrong number. You can choose only: 1, 2, 3");
	        		}else {
	        			isValidInput = true;
	        		}
	            }catch(InputMismatchException e) {//6: обробка винятків
	            	System.out.println("Invalid input. Please enter integer type");
	            	s.nextLine();
	            }
	        }
	}
	
	public void inputDouble() {//7: метод для введення с
		 boolean isValidInput = false;
		System.out.println("input value of \"c\": ");

		 while(!isValidInput) {//8: початок циклу
			 s = new Scanner(System.in);
			try { 
				c = s.nextDouble();//9: зміна значення c
				isValidInput = true;
			}catch(InputMismatchException e) {//10: обробка винятків
				System.out.println("you print invalid type of c. Please enter integer or double");
				s.nextLine();
			}
		 }
	}
	
	public double makeOperation(int b, double c) throws IllegalArgumentException, ArithmeticException{//11: метод обчислення 
		double a = 0;
			if(b == 1) {//12: перевірка умови №1
				a = (4 * b) - (9 * c);//13: змінити значення а
			}else if(b == 2) {//14: інакше перевірка умови №2
				a = Math.sqrt(2 - b * c);//15: змінити значення а
				if(Double.isNaN(a)) {//16: перевірити чи а існує
					throw new IllegalArgumentException("Invalid arguments: b = " + b + ", c = " + c);//17: викинути виняток
				}			
			}else if(b == 3) {//18: інакше перевірити умову №3
				if(c == 0) {//19: перевірити чи с != 0
					throw new ArithmeticException("Division by zero.");//20: викинути виняток
				}else { 
					a = b / Math.pow(c, 2);//21: змінити значення а
				}		
			}		

		return a;
	}
	
	public boolean isComplete() {//24: метод вибору дії
	    boolean isValidInput = false;

	    System.out.println("complete the program?: (yes or no)");

	    while (!isValidInput) {//25: початок циклу
	    	runtime = s.next();
	        if (runtime.equals("yes") || runtime.equals("no")) {//26: перевірка умови
	            isValidInput = true;
	        } else {
	            System.out.println("the text is wrong. Please try one more: \"yes\" or \"no\"");
	        }
	    }

	    return isValidInput;
	}



}
