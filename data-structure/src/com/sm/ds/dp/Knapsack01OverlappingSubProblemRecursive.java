package com.sm.ds.dp;

/**
 * This is overlapping sub problem for optimal substructure using recursive approach 
 * in this case the time complexity is exponential 2^n
 * 
 * We can optimize this solution using dynamic programming tabulation approach
 */
public class Knapsack01OverlappingSubProblemRecursive
{

	public static void main(String[] args)
	{
		int[] val = {60, 100, 120};
		int[] wt = {10, 20, 30};
		int W = 50;
		int n = val.length;
		
		System.out.println("Max profit for weight: " + W + " is: " + knapsack(W, wt, val, n));
	}

	private static int knapsack(int W, int[] wt, int[] val, int n)
	{
		// Base case
		if(n == 0 || W == 0) return 0;
		
		// If weight of the nth item is greater than the knapsack capacity W
		// This item then can't be included in optimal solution
		if(wt[n-1] > W) return knapsack(W, wt, val, n);
		
		// Return the maximum of two cases
		// 1. Including the nth item (n-1) position
		// 2. Excluding the nth item
		
		return Math.max(val[n-1] + knapsack(W-wt[n-1], wt, val, n-1), 
					knapsack(W, wt, val, n-1));
	}

}
