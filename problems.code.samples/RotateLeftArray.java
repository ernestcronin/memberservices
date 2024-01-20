import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * From HackerRank
 * 
 * A left rotation operation on an array shifts each of the array's elements
 * unit to the left. For example, if left rotations are performed on array ,
 * then the array would become . Note that the lowest index item moves to the
 * highest index in a rotation. This is called a circular array.
 * 
 * Given an array of integers and a number, , perform left rotations on the
 * array. Return the updated array to be printed as a single line of
 * space-separated integers.
 */
public class RotateLeftArray {

	public static List<Integer> rotateLeft(List<Integer> a, int d) {

		Integer[] ints = new Integer[a.size()];

		int remainder = 0;

		if (d > a.size()) {
			remainder = d % a.size();
		}

		for (int i = 0; i < a.size(); i++) {

			int shift = 0;
			if (remainder != 0) {
				shift = i - remainder;
			} else {
				shift = i - d;
			}
			if (shift < 0) {
				int absVal = a.size() - (Math.abs(shift));
				ints[absVal] = a.get(i);
			} else {
				ints[shift] = a.get(i);
			}
		}
		return Arrays.asList(ints);
	}

	public static void main(String[] args) {

		int[] array = { 1, 2, 3, 4, 5 };
		int numOfRotations = 54;
		List<Integer> list = new ArrayList<>();
		Arrays.stream(array).boxed().forEach(list::add);

		List<Integer> result = rotateLeft(list, numOfRotations);
		result.stream().forEach(System.out::println);

	}
}
