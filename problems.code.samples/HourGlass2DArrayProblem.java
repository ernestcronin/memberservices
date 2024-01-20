

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * From Hacker Rank Given a 2D Array, :
 * 
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 
 * An hourglass is a subset of values with indices falling in this pattern in
 * the 2D's graphical representation:
 * 
 * a b c
 *	 d
 * e f g
 * 
 * There are 16 hourglasses in 6x6 array. An hourglass sum is the sum of an hourglass'
 * values. Calculate the hourglass sum for every hourglass in , then print the
 * maximum hourglass sum. The array will always be 6x6.
 * 
 */
public class HourGlass2DArrayProblem {

	
	
	

    public static int hourglassSum(List<List<Integer>> arr) {
        
        int[][] list = arr.stream().map(x -> x.stream().mapToInt(i ->i).toArray()).toArray(int[][]::new);
        
        List<Integer> results = new ArrayList<>();
        
        for(int i=0; i<=3; i++){
            
            
            for(int j=0; j<=3; j++){
                int sum = 0;
                sum+= list[i][j] + list[i][j+1] + list[i][j+2] + list[i+1][j+1] + list[i+2][j] 
                + list[i+2][ j+1] + list[i+2][j+2];  
                results.add(sum);
            }
            
        }
        
        int largest = 0;
        for(int i =0; i < results.size(); i++){
            if(i == 0){
                largest = results.get(i);
            }else{
                if(results.get(i) > largest){
                    largest = results.get(i);
                }
            }
        }
        
        return largest;
    }



    public static void main(String[] args) {
    	
    	int[][] twoDimArray = {
    			{1, 1, 1, 0, 0, 0},
    			{0, 1, 0, 0, 0 ,0},
    			{1, 1, 1, 0, 0, 0},
    			{0, 0, 2, 4 ,4 ,0},
    			{0, 0, 0, 2, 0, 0},
    			{0, 0, 1, 2, 4, 0} 			
    	};
    	
        List<List<Integer>> arr = Arrays.
                //Convert the 2d array into a stream.
                stream(twoDimArray).
                //Map each 1d array (internalArray) in 2d array to a List.
                map(
                        //Stream all the elements of each 1d array and put them into a list of Integer.
                		//Don't use Collector.list() anymore
                        internalArray -> Arrays.stream(internalArray).boxed().collect(toList()
                    )
        //Put all the lists from the previous step into one big list.
        ).toList();

        int result = HourGlass2DArrayProblem.hourglassSum(arr);

        System.out.println("Result: " + result);
    }
}
