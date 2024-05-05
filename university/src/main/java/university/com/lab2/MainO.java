package university.com.lab2;

import java.util.NoSuchElementException;

public class MainO {
    public static void main(String[] args) {
        int[] array = {1, 10, 3, 8, 2, 7, 6, 9, 4}; // Приклад масиву
        try {
        	int minLocalMax = findMinLocalMax(array);
        	System.out.println("Мінімальний локальний максимум: " + minLocalMax); //виводимо результат
        }catch(NoSuchElementException e) {//обробляємо виняток, коли в масиві немає локальних max
        	System.out.println("В масиві немає локальних максимумів");
        }
        
    }

    public static int findMinLocalMax(int[] array) throws NoSuchElementException{
    	try {
	        if (array == null || array.length < 3) { // перевіряємо чи масив має хочаб 3 елементи
	            throw new IllegalArgumentException();
	        }
	    }catch(IllegalArgumentException e) {//обробляємо виняток коли в масиві не достатньо елементів
	    	System.out.println("даний масив пустий або не містить хоча б 3 елементи");
	    }

        int minLocalMax = Integer.MAX_VALUE; //оголошуємо змінну для результату

        for (int i = 1; i < array.length - 1; i++) { //проходимось по всьому масиву
            if (array[i] > array[i - 1] && array[i] > array[i + 1]) { // Перевіряємо, чи є поточний елемент локальним максимумом
                if(minLocalMax > array[i]) { // якщо даний елемент менший за поточний результат
                	minLocalMax = array[i]; // Оновлюємо мінімальний локальний максимум
                }
            }
        }

        if (minLocalMax == Integer.MAX_VALUE) { //перевіряємо чи було знайдено хочаб один локальний максимум
            throw new NoSuchElementException("");
        }
        return minLocalMax;
    }
}
