import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int[][] array = new int[0][0];

    public static void main(String[] args) {

        int ans;

        do {
            System.out.println("Выберите пункт меню: " + "\n");
            System.out.println("1) Ввод матрицы в ручную ");
            System.out.println("2) Ввод матрицы рандомно ");
            System.out.println("3) Вывод матрицы на экран ");
            System.out.println("4) Сортировка матрицы по строкам ");
            System.out.println("5) Поиск значения в строке матрицы ");
            System.out.println("6) Решение задачи №10 ");
            System.out.println("7) Выход из программы ");
            System.out.print("Выбрать пункт меню: ");
            ans = scanner.nextInt();
            scanner.nextLine();

            switch (ans) {
                case 1:
                    fillByHand();
                    break;

                case 2:
                    fillByRandom();
                    break;

                case 3:
                    printArray();
                    break;

                case 4:
                    int searchChoice;
                    do {
                        System.out.println("Методы сортировки по строкам: ");
                        System.out.println("1. Сортировка Шелла: ");
                        System.out.println("2. Быстрая сортировка: ");
                        System.out.println("3. Встроенная сортировка: ");
                        System.out.println("4. Сравнение времени сортировок: ");
                        System.out.println("5. Вернуться назад");
                        System.out.print("Выберите метод поиска подстроки: ");
                        searchChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (searchChoice) {
                            case 1:
                                shellSort();
                                break;

                            case 2:
                                quickSortMatrix();
                                break;

                            case 3:
                                builtInSort();
                                break;

                            case 4:
                                compareSortingTime();
                                break;

                            case 5:
                                System.out.println("Назад ");
                                break;
                            default:
                                System.out.println("Неверный пункт меню");
                        }
                    }
                    while (searchChoice != 5);
                    break;
                case 5:
                    int searchChoice1;
                    do {
                        System.out.println("Методы поиска значений в строке матрицы: ");
                        System.out.println("1 Последовательный поиск: ");
                        System.out.println("2 Бинарный поиск: ");
                        System.out.println("3 Фибоначчиев поиск: ");
                        System.out.println("4 Интерполяционный поиск: ");
                        System.out.println("5 Поиск встроенной функцией: ");
                        System.out.println("6 Сравнение времени алгоритмов поиска: ");
                        System.out.println("7 Вернуться назад");
                        System.out.print("Выберите метод поиска подстроки: ");
                        searchChoice1 = scanner.nextInt();
                        scanner.nextLine();
                        switch (searchChoice1) {
                            case 1:
                                sequentialSearchInSTR();
                                break;

                            case 2:
                                System.out.print("Введите значение для поиска: ");
                                int targetValue = scanner.nextInt();
                                scanner.nextLine();
                                // Вызываем метод бинарного поиска в каждой строке матрицы
                                for (int i = 0; i < array.length; i++) {
                                    int[] sortedSTR = quickSortRow(Arrays.copyOf(array[i], array[i].length));
                                    binarySearchInSTR(sortedSTR, targetValue);
                                }
                                break;

                            case 3:
                                System.out.println("Введите значение для поиска: ");
                                int keyValueFibonacci = scanner.nextInt();
                                fibonacciSearchInMatrix(keyValueFibonacci);
                                break;


                            case 4:
                                interpolationSearchInMatrix();
                                break;

                            case 5:
                                builtInSearchInMatrix();
                                break;

                            case 6:
                                compareSearchMethodsTime();
                                break;

                            case 7:
                                System.out.println("Назад ");
                                break;
                            default:
                                System.out.println("Неверный пункт меню");
                        }
                    }
                    while (searchChoice1 != 7);
                    break;
                case 6:
                    solveTask10();
                    break;

                case 7:
                    System.out.println("Выход из программы ");
                    break;
                default:
                    System.out.println("ОШИБКА ввода");
            }
        }
        while (ans != 7);
    }

    public static void fillByHand() {
        System.out.println("Введите кол-во строк: ");
        int str = scanner.nextInt();
        System.out.println("Введите кол-во столбцов: ");
        int stb = scanner.nextInt();
        array = new int[str][stb];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < str; i++) {
            System.out.println("Строка № " + (i + 1) + " :");
            for (int j = 0; j < stb; j++) {
                System.out.println("Элемент № " + (j + 1) + ": ");
                array[i][j] = scanner.nextInt();
            }
        }
    }


    public static void fillByRandom() {
        System.out.println("Введите кол-во строк: ");
        int str = scanner.nextInt();
        System.out.println("Введите кол-во столбцов: ");
        int stb = scanner.nextInt();
        array = new int[str][stb];
        for (int i = 0; i < str; i++) {
            for (int j = 0; j < stb; j++) {
                array[i][j] = random.nextInt(2001) - 1000; // заполняет случайными значениями от -1000 до 1000
            }
        }
    }


    public static void printArray() {
        System.out.println("Двумерный массив: ");
        if (array.length == 0) {
            System.out.println("\n Массив не существует");
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }
        }
    }


    static int[][] shellSort() {
        int rows = array.length;
// Проходим по каждой строке
        for (int i = 0; i < rows; i++) {
            int[] row = array[i];
            int n = row.length;
            int d = n / 2;
//Сортируем текущую строку
            while (d > 0) {
                for (int j = d; j < n; j++) {
                    int temp = row[j];
                    int k = j;

                    while (k >= d && row[k - d] > temp) {
                        row[k] = row[k - d];
                        k -= d;
                    }

                    row[k] = temp;
                }
                // уменьшаем шаг для следующей итерации
                d /= 2;
            }
        }
        return array;
    }

    public static void quickSortMatrix() {
        int rows = array.length;

        for (int i = 0; i < rows; i++) {
            array[i] = quickSortRow(array[i]);
        }
    }

    public static int[] quickSortRow(int[] row) {
        if (row.length <= 1) {
            return row;
        }

        int pivot = row[row.length - 1];
        int i = -1;

        for (int j = 0; j < row.length - 1; j++) {
            if (row[j] < pivot) {
                i++;

                int temp = row[i];
                row[i] = row[j];
                row[j] = temp;
            }
        }

        int temp = row[i + 1];
        row[i + 1] = row[row.length - 1];
        row[row.length - 1] = temp;

        int[] leftPart = Arrays.copyOfRange(row, 0, i + 1);
        int[] rightPart = Arrays.copyOfRange(row, i + 2, row.length);

        int[] result = new int[row.length];
        for (int j = 0; j < leftPart.length; j++) {
            result[j] = leftPart[j];
        }

        result[leftPart.length] = pivot;

        for (int j = 0; j < rightPart.length; j++) {
            result[leftPart.length + 1 + j] = rightPart[j];
        }

        return result;
    }


    public static void builtInSort() {
        int rows = array.length;

        for (int i = 0; i < rows; i++) {
            Arrays.sort(array[i]);
        }
    }


    public static void compareSortingTime() {
        long shellSortTime = measureSortingTime(Main::shellSort);
        long quickSortTime = measureSortingTime(Main::quickSortMatrix);
        long builtInSortTime = measureSortingTime(Main::builtInSort);

        System.out.println("Сортировка Шелла заняла " + shellSortTime + " наносекунд.");
        System.out.println("Быстрая сортировка заняла " + quickSortTime + " наносекунд.");
        System.out.println("Встроенная сортировка заняла " + builtInSortTime + " наносекунд.");
    }

    private static long measureSortingTime(Runnable sortingMethod) {
        long startTime = System.nanoTime();
        sortingMethod.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }


//CASE 5
public static void sequentialSearchInSTR() {
    int key = getSearchValueInSTR(); // Теперь используем новый метод для ввода значения
    boolean found = false;

    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
            if (array[i][j] == key) {
                System.out.println("Значение " + key + " найдено в строке " + (i + 1) + ", столбце " + (j + 1));
                found = true;
                break;
            }
        }
        if (found) {
            break;
        }
    }

    if (!found) {
        System.out.println("Значение " + key + " не найдено в матрице");
    }
}
    // Метод для ввода значения для поиска в строке матрицы
    public static int getSearchValueInSTR() {
        System.out.print("Введите значение для поиска в строке: ");
        return scanner.nextInt();
    }



    public static boolean binarySearchInSTR(int[] sortedSTR, int targetValue) {
        int left = 0;
        int right = sortedSTR.length - 1;
        boolean valueFound = false;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sortedSTR[mid] == targetValue) {
                valueFound = true;
                break;
            } else if (sortedSTR[mid] < targetValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (valueFound) {
            System.out.println("Значение " + targetValue + " найдено в строке");
        } else {
            System.out.println("Значение " + targetValue + " не найдено в строке");
        }
return valueFound;
    }




    public static void fibonacciSearchInMatrix(int keyValue) {
        int rows = array.length;
        int foundRow = -1;
        int foundColumn = -1;
        boolean valueFound = false;

        for (int i = 0; i < rows; i++) {
            int[] currentRow = array[i];
            int n = currentRow.length;

            if (n > 0 && fibonacciSearch(currentRow, keyValue, n)) {
                foundRow = i;
                foundColumn = findColumnInRow(currentRow, keyValue);
                valueFound = true;
                break;
            }
        }

        if (valueFound) {
            System.out.println("Значение найдено в строке " + (foundRow + 1) + " столбце " + (foundColumn + 1));
        } else {
            System.out.println("Значение не найдено");
        }
    }

    public static boolean fibonacciSearch(int[] array, int key, int n) {
        int fib2 = 0;
        int fib1 = 1;
        int fib = fib2 + fib1;

        while (fib < n) {
            fib2 = fib1;
            fib1 = fib;
            fib = fib2 + fib1;
        }

        int offset = -1;

        while (fib > 1) {
            int i = Math.min(offset + fib2, n - 1);

            if (array[i] < key) {
                fib = fib1;
                fib1 = fib2;
                fib2 = fib - fib1;
                offset = i;
            } else if (array[i] > key) {
                fib = fib2;
                fib1 = fib1 - fib2;
                fib2 = fib - fib1;
            } else {
                return true;
            }
        }

        if (fib1 == 1 && array[offset + 1] == key) {
            return true;
        }

        return false;
    }
    // Новый метод для поиска столбца в строке
    public static int findColumnInRow(int[] row, int key) {
        for (int j = 0; j < row.length; j++) {
            if (row[j] == key) {
                return j;
            }
        }
        return -1; // Если не найдено, возвращаем -1
    }



    public static void interpolationSearchInMatrix() {
        System.out.print("Введите значение для поиска: ");
        int keyValue = scanner.nextInt();
        scanner.nextLine();

        int rows = array.length;
        boolean found = false;

        for (int i = 0; i < rows; i++) {
            int[] sortedRow = quickSortRow(Arrays.copyOf(array[i], array[i].length));
            int result = interpolationSearch(sortedRow, keyValue);  // для каждой строки используется метод interpolationSearch

            if (result != -1) {
                System.out.println("Значение " + keyValue + " найдено в строке " + (i + 1) + ", столбце " + (result + 1));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Значение " + keyValue + " не найдено в матрице");
        }
    }
    // Метод интерполяционного поиска для одномерного массива
    public static int interpolationSearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;  // лоу и хайт границы поиска в массиве

        while (low <= high && key >= array[low] && key <= array[high]) {
            if (low == high) {
                if (array[low] == key) {
                    return low;    // если элемент найден он возвращается , если нет то границы обновляются
                }
                return -1;
            }

            int pos = low + ((key - array[low]) * (high - low)) / (array[high] - array[low]);

            if (array[pos] == key) {
                return pos;
            }

            if (array[pos] < key) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;
    }



    public static void builtInSearchInMatrix() {
        System.out.print("Введите значение для поиска: ");
        int keyValue = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {  // цикл фор проходит по каждому столбцу и строке
                if (array[i][j] == keyValue) {  // в каждой иттерации проверяется найдено значение или нет
                    System.out.println("Значение " + keyValue + " найдено в строке " + (i + 1) + ", столбце " + (j + 1));
                    found = true;

                }
            }
            if (found) {
            }
        }
        if (!found) {
            System.out.println("Значение " + keyValue + " не найдено в матрице");
        }
    }



    public static void compareSearchMethodsTime() {
        int targetValue = getSearchValueInSTR();

        long startTime;
        long endTime;

        // Sequential Search
        startTime = System.nanoTime();
        sequentialSearchInSTR();
        endTime = System.nanoTime();
        System.out.println("Последовательный " + (endTime - startTime) + " наносекунд");

        // Binary Search
            int[] sortedStr = quickSortRow(Arrays.copyOf(array[0], array[0].length));
            startTime = System.nanoTime();
            binarySearchInSTR(sortedStr, targetValue);
            endTime = System.nanoTime();
            System.out.println("Бинарный поиск: " + (endTime - startTime) + " наносекунд");

        // Fibonacci Search
        startTime = System.nanoTime();
        fibonacciSearchInMatrix(targetValue);
        endTime = System.nanoTime();
        System.out.println("Поиск Фибоначчи: " + (endTime - startTime) + " наносекунд");

        // Interpolation Search
        int[] sortedRowInterpolation = quickSortRow(Arrays.copyOf(array[0], array[0].length));
        startTime = System.nanoTime();
        interpolationSearch(sortedRowInterpolation, targetValue);
        endTime = System.nanoTime();
        System.out.println("Интерполяционный поиск:" + (endTime - startTime) + " наносекунд");

        // Built-in Search
        startTime = System.nanoTime();
        builtInSearchInMatrix();
        endTime = System.nanoTime();
        System.out.println("Встроенный поиск: " + (endTime - startTime) + " наносекунд");
    }



    public static void solveTask10() {

        System.out.print("Введите размер квадрата: ");
        int N = scanner.nextInt(); // Размер квадратного массива
        int[][] array = new int[N][N];

        // Заполнение массива частично случайными значениями (1 или 0)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                array[i][j] = (int) (Math.random() * 2);
            }
        }

        System.out.print("Введите размер квадрата для поиска: ");
        int M = scanner.nextInt(); // Размер искомого квадрата

        checkAndPrintSquare(array, M);
    }

    public static void checkAndPrintSquare(int[][] array, int M) {
        int N = array.length;
        boolean squareFound = false;

        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j <= N - M; j++) {
                // проверка возможности размещения квадрата MxM в массиве
                boolean squareFits = true;
                for (int k = i; k < i + M; k++) {
                    for (int l = j; l < j + M; l++) {
                        if (array[k][l] != 1) {  // проверяем, что значение равно 1
                            squareFits = false;
                            break;
                        }
                    }
                    if (!squareFits) {
                        break;
                    }
                }
                // если квадрат размещается, вывести этот квадратный массив и измененный квадрат
                if (squareFits) {
                    squareFound = true;
                    // Вывод изначального массива
                    System.out.println("Изначальный массив:");
                    for (int k = 0; k < N; k++) {
                        for (int l = 0; l < N; l++) {
                            System.out.print(array[k][l] + " ");
                        }
                        System.out.println();
                    }
                    // Вывод квадрата размером MxM и его позиции
                    System.out.println("Квадрат размером " + M + "x" + M + " на позиции [" + i + ";" + j + "]:");
                    for (int k = i; k < i + M; k++) {
                        for (int l = j; l < j + M; l++) {
                            System.out.print(array[k][l] + " ");
                        }
                        System.out.println();
                    }
                }
            }
        }
        if (!squareFound) {
            System.out.println("Невозможно разместить квадрат размером " + M + " в данном массиве");
        }
    }
}
