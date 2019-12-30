package com.sm.ds.tree.range;

import java.util.Arrays;

public class RangeMinST
{
	private static int[] st;

	public static void main(String[] args)
	{
		int[] arr = {1, 3, 2, 7, 9, 11};
		int n = arr.length;
		
		// Build segment tree from the given array
		buildST(arr, n);
//		System.out.println(Arrays.toString(st));
		
		int qs = 3;
		int qe = 5;
		
		 // Print minimum value in arr[qs..qe] 
        System.out.println("Minimum of values in range [" + qs + ", "
                           + qe + "] is = " + findRangeMinimum(n, qs, qe));
	}

	private static int findRangeMinimum(int n, int qs, int qe)
	{
		// Check for invalid input
		if(qs < 0 || qe > n-1 || qs > qe)
		{
			System.out.println("Invalid input ..... ");
			return -1;
		}
		
		// For valid range
		return findRangeMinimum(0, n-1, qs, qe, 0);
	}

	private static int findRangeMinimum(int ss, int se, int qs, int qe, int index)
	{
		// If the provided range completely overlaps the segment range
		if(qs <= ss && qe >= se)
		{
			return st[index];
		}
		
		// If provided range is completely outside of segment range, return MAX_VALUE
		if(qs > se || qe < ss )
		{
			return Integer.MAX_VALUE;
		}
		
		// If the provided range partially overlaps then we need to find the min of left and right
		int mid = getMid(ss, se);
		return Math.min(findRangeMinimum(ss, mid, qs, qe, 2*index+1),
							findRangeMinimum(mid+1, se, qs, qe, 2*index+2));
	}

	private static void buildST(int[] arr, int n)
	{
		// Height of the segment tree
		// Total nodes would be n(leaf nodes) + (n-1) internal nodes (in full binary tree) = 2n-1
		// The height of the ST will be log(n) (base 2)
		// Since tree is represented in form of array and relation between parent and child is maintained
		// so the size of the memory allocated for segment tree will be 2*2^log(n) (base 2) - 1
		int x = (int) Math.ceil(Math.log(n) / Math.log(2));
		int stSize = 2 * (int) Math.pow(2, x) - 1;
		st = new int[stSize];
		
		
		// Build the ST
		buildST(arr, 0, n-1, 0);
		
	}

	private static int buildST(int[] arr, int ss, int se, int si)
	{
		// If array contains only one element return that element with st build
		if(ss == se)
		{
			st[si] = arr[ss];
			return arr[ss];
		}
		
		// For more elements in the array, call recursively to fill the left and right element
		// with minimum of its sub nodes (left and right child)
		int mid = getMid(ss, se);
		st[si] = Math.min(buildST(arr, ss, mid, 2*si+1), buildST(arr, mid+1, se, 2*si+2));
		
		// dummy return
		return st[si];
		
		
	}

	private static int getMid(int ss, int se)
	{
		return ss + (se - ss) / 2;
	}

}
