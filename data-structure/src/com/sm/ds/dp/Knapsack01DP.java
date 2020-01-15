package com.sm.ds.dp;

public class Knapsack01DP
{

	public static void main(String[] args)
	{
		int[] wt = new int[] {1, 2, 3};
		int[] profit = new int[] {60, 100, 120};
		final int W = 5;
		int n = wt.length;
		
		int maxProfit = knapsack(W, wt, profit, n); // 3 is number of items 
		System.out.println("MAX Profit: " + maxProfit);
		
	}
	
	public static int knapsack(int W, int[] wt, int[] profit, int n)
	{
		int i, w;
		int[][] K = new int[n+1][W+1];
		
		// Build the K
		for(i = 0; i <= n; i ++)
		{
			for(w = 0; w <= W; w++)
			{
				if( i == 0 || w == 0 )
				{
					K[i][w] = 0;
				}
				else if( wt[i-1] <= w )
				{
					// Return the maximum of two cases
					// 1. Including the nth item (n-1) position
					// 2. Excluding the nth item
					K[i][w] = Math.max(profit[i-1] + K[i-1][w-wt[i-1]],
							K[i-1][w]);
				}
				else
				{
					// If weight of current item > capacity
					// we will hold the max profit excluding this item, which is already calculated
					K[i][w] = K[i-1][w]; 
				}
			}
		}
		
		return K[n][W];
	}

}
