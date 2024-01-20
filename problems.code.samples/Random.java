


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Random questions I've been asked over time
 */
public class Random {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    class Employee {
        private Integer id;

        private String name;

        private Double salary;

        public void incrementSalary(Double value) {
            this.salary = salary + value;
        }
    }


    public Employee createEmployee(Integer id, String name, Double salary) {
        return new Employee(id, name, salary);
    }


    public static void main(String[] args) {
        
    	System.out.println(isPrimeNumber(11));

        int[][] array = {{2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};

        int[][] array1 = {{-7, 100, -100, 59}, {200, -1000, 1, 5}, {1, 1, -70, 300}};
        printSumOfRows2DArray(array);

        printSumOfCols2DArray(array);

        printLargestValOfEachRow(array1);
    }

    public static void ternaryAssignment() {
        Random test = new Random();

        Employee e1 = test.createEmployee(1, "ernest", 1d);
        Employee e2 = test.createEmployee(2, "mike", 2d);
        boolean same = Objects.equals(e1.id, e2.id);
        System.out.println(same);

    }

    //** function to find a prime number
    public static boolean isPrimeNumber(int number) {

        int[] list = {-2, -1, 0, 1, 2};
        for (int i = 0; i < list.length; i++) {
            if (list[i] == number) return false;
        }

        if (number % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= (number / 2); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    //using stream
    public static void stream() {

        Random test = new Random();

        Employee[] arrayOfEmps = {test.createEmployee(1, "Jeff Bezos", 100000.0), test.createEmployee(2, "Bill Gates",
                200000.0), test.createEmployee(3, "Mark Zuckerberg", 300000.0)};

        Stream<Employee> stream = Stream.of(arrayOfEmps);
        stream.forEach(e -> e.incrementSalary(50.0));

        Stream<Employee> stream2 = Stream.of(arrayOfEmps);
        stream2.forEach(e -> System.out.println(e.toString()));
    }

    //iterate over a map
    public static void iterateMap() {

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "sandy");
        map.put(2, "dylan");
        map.put(3, "ernest");

        Iterator<Entry<Integer, String>> it = map.entrySet()
                .iterator();
        while (it.hasNext()) {
            Entry<Integer, String> entry = it.next();
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }

    //problem to count words in a string
    public static int countWords(String str) {

        Map<Integer, String> map = new HashMap<>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (!map.containsValue(words[i])) {
                map.put(i, words[i]);
            }
        }
        return map.size();
    }

    //swap with 3 vars
    public static void swap(int x, int y) {
        int sum = x + y;
        x = y;
        y = sum - x;
        System.out.println("x " + x + " y " + y);
    }

    //swap with 2 vars
    public static void swap2(int x, int y) {

        x = y + x;
        y = x - y;
        x = x - y;
        System.out.println("x " + x + " y " + y);
    }

    
    //reverse a string
    //stringBuffer is no longer preferred due to syncronization
    public static String reverse(String str) {
        StringBuilder buffer = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            buffer.append(str.charAt(i));
        }
        buffer.toString();

        //OR

        StringBuilder build = new StringBuilder();
        build.append(str);
        return build.reverse()
                .toString();

    }

    public static int xn(int value, int x, int n) {
        if ((x == 1 && value == 0) || (n == 0 && value == 0)) return 1;
        if (n == 1) return value;
        if (value == 0) value = x * x;
        else {
            value = value * x;
        }
        return xn(value, x, n - 1);
    }


    //find fibannaci seq
    public static int getFibNumb(int limit) {
        int first = 0;
        int second = 1;
        int x = 0;

        if (limit == 0) {
            return first;
        } else if (limit == second) {
            return second;
        }

        for (int i = 3; i <= limit; i++) {
            x = first + second;
            first = second;
            second = x;
        }
        return x;
    }

    //count number of char in digit
    public static int countDigits(int number) {

        String numb = String.valueOf(number);
        return numb.length();

    }

    //print sum of rows in a 2D array
    public static void printSumOfRows2DArray(int[][] array) {

        int row = array.length;
        int col = array[0].length;

        for (int i = 0; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < col; j++) {

                sum += array[i][j];
            }
            System.out.println("Sum of row " + (i + 1) + " is: " + sum);
        }
    }

    //print sum of cols in a 2D array
    public static void printSumOfCols2DArray(int[][] array) {

        int row = array.length;
        int col = array[0].length;

        for (int i = 0; i < col; i++) {
            int sum = 0;
            for (int j = 0; j < row; j++) {

                sum += array[j][i];
            }
            System.out.println("Sum of col " + (i + 1) + " is: " + sum);
        }
    }

    //print largest value in each row of a 2d array
    public static void printLargestValOfEachRow(int[][] array) {

        int row = array.length;
        int col = array[0].length;

        for (int i = 0; i < row; i++) {
            int largest = 0;
            for (int j = 0; j < col; j++) {

                if (j == 0) {
                    largest = array[i][j];
                } else {
                    if (largest < array[i][j]) {
                        largest = array[i][j];
                    }
                }
            }
            System.out.println("Largest value in row " + (i + 1) + " is: " + largest);
        }
    }
}
