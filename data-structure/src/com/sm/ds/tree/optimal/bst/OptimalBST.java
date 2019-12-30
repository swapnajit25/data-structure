package com.sm.ds.tree.optimal.bst;

public class OptimalBST
{

	public static void main(String[] args)
	{
		int[] keys = {10, 12, 20};
		int[] freq = {3, 1, 5};
		int n = keys.length;
		
		System.out.println("Cost of optimal BST is: " + optimalsearchTree(keys, freq, n));
	}

	public static int optimalsearchTree(int[] keys, int[] freq, int n)
	{
		// Create a cost matrix 2D
		int[][] cost = new int[n][n];
		
		// For single key(node), cost is equals to the frequency
		for(int i = 0; i < n; i++)
		{
			cost[i][i] = freq[i];
		}
		
		// Now we need to go on adding 1 by 1 node, such the chain length will
		// be 2, 3, ... and so on
		// L is the chain length
		for(int L = 2; L <= n; L++ )
		{
			// Now i is the row number in cost matrix starts from 0
			for(int i = 0; i <= (n-L); i++)
			{
				// Get the column number j from row i and chain length L
				int j = i+L-1;
				cost[i][j] = Integer.MAX_VALUE;
				
				// Try making all the keys in the interval [i..j] as root denoted by r
				for(int r = i; r <= j; r++)
				{
					// Find cost when keys[r] become the root node
					int c = ((r > i)? cost[i][r-1] : 0) +
								((r < j)? cost[r+1][j] : 0) + sum(freq, i, j);
					
					// Update the minimum cost if being root as r, current cost is less than prev root cost
					if(c < cost[i][j])
					{
						cost[i][j] = c;
					}
					
				}
			}
		}
		// The cost of right cornered cell will be the minimum  
		return cost[0][n-1];
	}

	private static int sum(int[] freq, int i, int j)
	{
		int sum = 0;
		for(int k = i; k <= j; k++)
		{
			sum += freq[k];
		}
		return sum;
	}

}
