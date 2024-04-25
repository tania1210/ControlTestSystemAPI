package university.com.lab2;

public class MainO {
    public static void main(String[] args) {
//        int[] array = {1, 10, 3, 8, 2, 7, 6, 9, 4}; // Приклад масиву
        int[] array = {};
        try {
        	int minLocalMax = findMinLocalMax(array);
        	System.out.println("Мінімальний локальний максимум: " + minLocalMax); //виводимо результат
        }catch(IllegalArgumentException e) {
        	
        }
        
    }

    public static int findMinLocalMax(int[] array) throws IllegalArgumentException{
    	
        if (array == null || array.length < 3) { // перевіряємо чи масив має хочаб 3 елементи
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException("В масиві немає локальних максимумів");
        }
        return minLocalMax;
    }
}
