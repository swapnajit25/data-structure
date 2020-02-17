package com.sm.ds.tree.range;

import java.util.Arrays;

/**
 * This tree is also called Binary Index Tree
 * The time complexity of range query is O(log n)
 * Time complexity to update an index is O(log n)
 */
public class FenwickTree
{
	private int[] biTree;

	public static void main(String[] args)
	{
		final int[] arr = {1, -7, 15, 9, 4, 2, 0, 10};
		final int n = arr.length;
		
		FenwickTree fwTree = new FenwickTree();
		fwTree.constructBITree(arr, n);
		
		System.out.println("Sum of elements in arr[0..5]"+ 
                " is "+ fwTree.getSum(5)); 
		
		System.out.println("Sum of elements in arr[2..5]"+ 
                " is "+ fwTree.getSum(2,5)); 
		
		System.out.println(Arrays.toString(fwTree.biTree));
		
	}
	
	/**
	 * Finds the sum between range 
	 */
	private int getSum(final int index1, final int index2)
	{
		return getSum(index2) - getSum(index1-1);
	}

	/**
	 * This method returns sum till 0 --- index
	 */
	private int getSum(int index)
	{
		int sum = 0;
		
		// Index in fwTree is 1 more than index int arr[]
		index = index + 1;
		
		// Traverse the ancestor of binary index tree
		while(index > 0)
		{
			sum += biTree[index];
			
			// Move the index to parent node
			index -= index & (-index);
		}
		
		return sum;
	}

	private void constructBITree(final int[] arr, final int n)
	{
		biTree = new int[n+1];
		// Store the value in binary index tree
		// Here in arr 0th index is 0+1 = 1st index in BI tree
		for(int i = 0; i < n; i++)
		{
			updateBIT(n, i, arr[i]);
		}
	}

	private void updateBIT(final int n, int index, final int val)
	{
		// Index in biTree[] is 1 more than index in arr[]
		index = index + 1;
		
		while(index <= n)
		{
			biTree[index] += val;
			
			// update the index
			index += index & (-index);
		}
	}

}


