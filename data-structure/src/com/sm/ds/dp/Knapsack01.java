package com.sm.ds.dp;

public class Knapsack01
{

	public static void main(String[] args)
	{
		int[] wt = new int[] {0, 10, 20, 30};
		int[] profit = new int[] {0, 60, 100, 120};
		final int W = 50;
//		int n = wt.length;
		
		int maxProfit = knapsack(W, wt, profit, 3); // 3 is number of items 
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
				else if( wt[i] <= w )
				{
					K[i][w] = Math.max(K[i-1][w], profit[i] + K[i-1][w-wt[i]]);
				}
				else
				{
					K[i][w] = K[i-1][w]; 
				}
			}
		}
		
		return K[n][W];
	}

}
