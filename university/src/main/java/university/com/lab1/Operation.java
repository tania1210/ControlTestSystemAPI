package university.com.lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Operation {
	static Scanner s = new Scanner(System.in);
	static int b; 
	static double c;
	static String runtime;
	
	public void setB(int b) {
		this.b = b;
	}
	
	public void setC(double c) {
		this.c= c;
	}
	
	public static int getB() {
		return b;
	}
	
	public static double getC() {
		return c;
	}
	
	public static String getRuntime() {
		return runtime;
	}
	
    public static void main(String[] args) {
    	Operation o = new Operation();
        do {
        	o.inputInteger();                            
            o.inputDouble();
        	try {
                System.out.println("result: " + o.makeOperation(b, c) + "\n"); // Вузол 3: передача даних змінних у метод та отримання результату               
    		}catch (IllegalArgumentException e) {
    	        System.out.println("invalid arguments: " + e); // Вузол 5: Виведення винятку
    	    }catch (ArithmeticException e) {
    	        System.out.println("division by zero: " + e); // Вузол 6: Виведення винятку
    	    }
        	o.isComplete();
        } while (!runtime.equals("yes"));

        s.close(); // Вузол 7: виклик методу close()

    }
	
	public void inputInteger() { 
		 boolean isValidInput = false;
		 
     	System.out.println("choose value of \"b\": (1, 2, 3)");

	        while (!isValidInput) {
	        	s = new Scanner(System.in);
	            try {
	        		b = s.nextInt();
	        		if(b < 1 || b > 3) {
	        			System.out.println("wrong number. You can choose only: 1, 2, 3");
	        		}else {
	        			isValidInput = true;
	        		}
	            }catch(InputMismatchException e) {
	            	System.out.println("Invalid input. Please enter integer type");
	            	s.nextLine();
	            }
	        }
	}
	
	public void inputDouble() { 
		 boolean isValidInput = false;
		System.out.println("input value of \"c\": ");

		 while(!isValidInput) {
			 s = new Scanner(System.in);
			try {
				c = s.nextDouble();
				isValidInput = true;
			}catch(InputMismatchException e) {
				System.out.println("you print invalid type of c. Please enter integer or double");
				s.nextLine();
			}
		 }
	}
	
	public double makeOperation(int b, double c) {
		double a = 0; // Вузол 11: Явне оголошення змінної

			if(b == 1) {
				a = (4 * b) - (9 * c); // Вузол 12: Обчислення значення а
			}else if(b == 2) {
				a = Math.sqrt(2 - b * c); // Вузол 13: Обчислення значення а
				if(Double.isNaN(a)) {
					throw new IllegalArgumentException("Invalid arguments: b = " + b + ", c = " + c);
					// Вузол 14: Викидання винятку
				}			
			}else if(b == 3) {
				if(c == 0) {
					throw new ArithmeticException("Division by zero."); // Вузол 15: Викидання винятку
				}else {
					a = b / Math.pow(c, 2); // Вузол 16: Обчислення значення а
				}		
			}		

		return a; // Вузол 17: Повернення нового значення а 
	}
	
	public boolean isComplete() {
	    boolean isValidInput = false;
//	    String runtimeInput = null;

	    System.out.println("complete the program?: (yes or no)");

	    while (!isValidInput) {
	    	runtime = s.next();
	        if (runtime.equals("yes") || runtime.equals("no")) {
	            isValidInput = true;
	        } else {
	            System.out.println("the text is wrong. Please try one more: \"yes\" or \"no\"");
	        }
//	    	try {
//	    		runtime = s.next();
//	    		System.out.println(runtime.equals("yes"));
//		        if (runtime.equals("yes") || runtime.equals("no")) {
//		            isValidInput = true;
//		        }else {
//		        	throw new InputMismatchException();
//		        }
//	    	}catch(InputMismatchException e) {
//	            System.out.println("the text is wrong. Please try one more: \"yes\" or \"no\"");
//
//	    	}
	    }

	    return isValidInput;
	}

}
