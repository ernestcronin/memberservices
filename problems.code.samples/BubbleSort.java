import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HackerRank problem
 * create a bubble sort function to sort a List of Integers. Print swaps, first and last.
 */
public class BubbleSort {

	/*
	 * Complete the 'countSwaps' function below.
	 *
	 * The function accepts INTEGER_ARRAY a as parameter.
	 */

	public static void countSwaps(List<Integer> a) {
		
		int count = 0;
		int[] arr = a.stream().mapToInt(i -> i).toArray();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					++count;
					int x = arr[j];
					int y = arr[j + 1];
					arr[j] = y;
					arr[j + 1] = x;
				}
			}
		}
		System.out.println("Array is sorted in " + count + " swaps.");
		System.out.println("First Element: " + arr[0]);
		System.out.println("Last Element: " + arr[arr.length - 1]);
	}
	
	public static void main(String[] args) {
		
		int[] arr = {10, 4, 5, 14, 1};
		
		List<Integer> list = new ArrayList<>();
		Arrays.stream(arr).forEach(list::add);
				
		countSwaps(list);	
	}
}
