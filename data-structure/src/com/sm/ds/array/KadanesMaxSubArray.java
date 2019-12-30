package com.sm.ds.array;

/**
 * Kadane's algorithm is used to find maximum sum sub-array 
 * in a few negative value array.
 * Time complexity O(n)  
 *
 */
public class KadanesMaxSubArray
{

	public static void main(String[] args)
	{
		int[] arr = {4, -2, -3, 4, -1, -2, 1, 8, -3};
		
		findMaximumSumSubArray(arr);

	}

	private static void findMaximumSumSubArray(int[] arr)
	{
		int startIndex = 0;
		int endIndex = 0;
		int maxSum = 0;
		int sumAsOfNow = 0;
		
		for(int i=0; i<arr.length; i++)
		{
			sumAsOfNow += arr[i];
			
			// Check and update maxSum if current sum is higher
			// and increment the endIndex
			if(sumAsOfNow > maxSum)
			{
				maxSum = sumAsOfNow;
				endIndex = i;
			}
			
			// If sumAsOfNow is negative we should ignore the sub-array till that
			// point and should again start from next index as start index.
			if(sumAsOfNow < 0)
			{
				sumAsOfNow = 0;
				startIndex = i+1;
			}
		}
		
		System.out.println("Maximum sum value: " + maxSum +" for sub array with"
				+ "	start index: " + startIndex + " to end index: " + endIndex);
	}

}
